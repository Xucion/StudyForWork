可以用 Redis 优化，但不建议用 Redis 完全替代 MySQL。更稳妥的是：

- MySQL：完整消息历史、审计记录、最终事实源。
- Redis：活跃会话的“热上下文”、结构化实体、摘要、幂等和短期状态。
- 模型调用前优先从 Redis 读取；缓存失效时从 MySQL 重建。

整体结构可以变成：

```
用户消息
   ↓
MySQL 持久化
   ↓
更新/失效 Redis 热上下文
   ↓
Redis 读取最近消息、摘要、焦点实体
   ↓
组装模型 Prompt
```

### Redis 中保存什么

建议按会话拆成几类 Key，并使用 `{conversationId}` 保证 Redis Cluster 下落在同一个槽位。

```
ai:ctx:{conversationId}:meta
ai:ctx:{conversationId}:messages
ai:ctx:{conversationId}:entities
ai:ctx:{conversationId}:summary
```

#### 1. 会话元数据

使用 Hash：

```
Key: ai:ctx:{c123}:meta

userId             = 1001
lastSequence        = 36
focusedOrderId      = 90001
contextVersion      = 12
status              = BOT_ACTIVE
updatedAt           = 1788105600
```

这里只存当前运行需要的状态，不保存完整会话对象。

#### 2. 最近消息窗口

可以使用 ZSet，以消息序号作为 score：

```
Key: ai:ctx:{c123}:messages

score=31  {"role":"USER","content":"查询订单90001"}
score=32  {"role":"ASSISTANT","content":"订单正在配送"}
score=33  {"role":"USER","content":"另外一单呢"}
```

ZSet 的优点是：

- 按 `sequenceNo` 排序；
- 可以读取指定消息之前的历史；
- 可以方便地保留最近 N 条；
- 重复写入时容易处理。

写入后执行裁剪：

```
ZREMRANGEBYRANK ai:ctx:{c123}:messages 0 -51
```

例如 Redis 保留最近 50 条，模型组装时再根据 Token 预算选择其中一部分，而不是固定取 12 条。

#### 3. 结构化实体上下文

针对多个订单，可以保存已验证实体：

```
Key: ai:ctx:{c123}:entities

order:90001 = {
  "type": "ORDER",
  "lastMentionSequence": 32,
  "status": "DELIVERING",
  "verified": true
}

order:90002 = {
  "type": "ORDER",
  "lastMentionSequence": 28,
  "status": "COMPLETED",
  "verified": true
}
```

当前焦点订单放在 `meta.focusedOrderId` 中。

这样：

- Redis 中保存多个相关订单；
- `focusedOrderId` 表示当前讨论对象；
- 多个订单无法唯一解析时，Agent 追问；
- Redis 丢失后可以从 MySQL 的消息和工具审计记录重建。

#### 4. 滚动摘要

当消息越来越多时，可以生成阶段性摘要：

```
Key: ai:ctx:{c123}:summary

summaryUntilSequence = 24
content = 用户主要询问订单90001和90002。
          90001正在配送，是当前焦点订单。
          用户曾询问退款，但尚未创建退款操作。
```

最终模型上下文可以组装为：

```
System Prompt
+ 历史摘要
+ 最近若干条原始消息
+ 当前用户问题
+ 已验证业务实体
+ 当前焦点订单
```

这比单纯扩大最近消息数量更节省 Token。

摘要需要特别注意：它只是理解上下文的辅助文本，不能作为订单状态、退款结果等业务事实源。业务事实仍要实时调用工具验证。

### 推荐的读写策略

使用 Cache-Aside 比较合适。

读取时：

```
1. 验证 conversationId 属于当前 userId
2. 查询 Redis 热上下文
3. 命中则直接组装上下文
4. 未命中则从 MySQL 读取会话、最近消息、实体
5. 重建 Redis，并设置 TTL
```

写入时：

```
1. 消息先提交 MySQL
2. 事务成功后更新 Redis
3. Redis 更新失败不影响消息持久化
4. 下次读取未命中或版本不一致时重新构建
```

不要采用“先写 Redis，再异步考虑是否写 MySQL”，否则服务崩溃时可能丢失用户消息。

### 避免数据库和 Redis 不一致

简单方案是在 MySQL 提交后删除缓存：

```
提交消息 → 删除 ai:ctx:{conversationId}:*
```

下一次请求自动重建。这种方案一致性最好，但缓存命中率略低。

性能更高的方案是在提交后增量更新 Redis，同时维护：

```
contextVersion
lastSequence
```

读取时比较 Redis 的 `lastSequence` 和数据库会话的 `last_message_sequence`。不一致就重建。

如果后续实例和消息量较多，可以采用：

- Transactional Outbox；
- Binlog CDC；
- 消息队列；

异步地将 MySQL 变更同步到 Redis，避免业务代码双写。

### Redis 还能优化的部分

除了模型上下文，还适合管理：

- 请求幂等：

  ```
  ai:idem:{userId}:{clientRequestId}
  ```

  使用 `SET key value NX EX 300` 快速拦截重复请求，但 MySQL 唯一约束仍要保留。

- Agent 正在生成状态：

  ```
  ai:processing:{conversationId}
  ```

  防止同一个会话并发触发多个 Agent 回答。

- SSE 事件短期重放：

  ```
  ai:sse:{conversationId}:{messageId}
  ```

  可以使用 Redis Stream 保存短期事件。

- 工具结果短缓存：

  门店状态可以短暂缓存；订单状态缓存时间应非常短，并在关键操作前重新查询。

- 限流：

  ```
  ai:rate:{userId}:{minute}
  ```

### TTL 建议

可以按数据性质区分：

- 热会话上下文：30 分钟至 24 小时；
- 正在处理标记：30～120 秒；
- 幂等结果：5～30 分钟；
- SSE 重放事件：5～15 分钟；
- 工具结果：几秒到几十秒；
- 会话摘要：可以长一些，但必须能够从 MySQL 重建。

每次用户继续对话时刷新热上下文 TTL。

### 安全边界

Redis Key 中即使包含 `conversationId`，读取时仍必须验证 `userId`。不能因为拿到了 Key 就认为用户有权访问。

Redis 中不要保存：

- 用户 JWT；
- 内部服务 JWT；
- 签名密钥；
- 完整电话和地址；
- 无必要的订单敏感字段；
- 未脱敏工具响应。

### 最适合当前项目的第一步

不需要一次性大改，可以分三步：

1. 先缓存 `ConversationContext`
   缓存最近完成消息、`relatedOrderId` 和 `lastSequence`，MySQL 继续作为唯一事实源。
2. 把单一 `relatedOrderId` 升级成实体集合
   Redis 保存活跃实体，MySQL增加会话实体表负责永久保存。
3. 增加“摘要 + Token 预算窗口”
   用历史摘要加最近消息替代固定 12 条窗口。

这样 Redis 的主要价值不是单纯“查询更快”，而是把 Agent 上下文升级成可快速读取、可过期、可重建的运行时状态，同时继续保留 MySQL 的可靠性和审计能力。
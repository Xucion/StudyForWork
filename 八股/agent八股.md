# 如何稳定输出json

**1. 提示模型：属于软约束**

例如提示：

> 只输出 JSON，必须包含 command 字段。

模型通常能遵守，但仍可能漏字段、写错类型，或者附带解释文字。提示词只能提高成功率。

**2. API 在生成时限制：属于硬约束**

一些 API 提供严格结构化输出，底层使用 **约束解码（constrained decoding）**。

模型每一步都要选择下一个 token。约束解码会根据 Schema 和已经生成的内容，排除不符合规则的候选 token，让生成过程沿着合法结构进行。

例如规定 `command` 是字符串，就不允许它生成：

```
{"command": 123}
```

Anthropic 的工具定义可以通过 `strict: True` 开启这种约束，前提是所用 API、模型和 Schema 支持它。

**但是Schema 本身可能没有表达出真正的业务规则**

因此需要：

**3. 程序收到结果后校验**

实际应用还可以在执行工具前，用 JSON Schema 校验库或 Pydantic 检查参数：

收到完整输出
    ↓
解析 JSON（SDK 可能已经完成）
    ↓
校验字段、类型、必填项
    ↓
检查业务条件和权限
    ↓
执行工具

# Hook

当执行工具前加权限检查、记录日志、检查输出等情况，每加一个功能，都得修改主循环；主循环也需要知道越来越多具体功能。

s04 在工具执行前、执行后等位置，安排了统一调用回调函数的代码。权限检查、日志等功能只要注册到对应位置，就会在那个时机执行。

s04 把这些功能挂到固定的扩展点上：

```
# 这里就是“工具执行前”的扩展点
trigger_hooks("PreToolUse", block)
# 真正执行工具
output = execute_tool(block)
trigger_hooks("PostToolUse", block, output)
```

然后在循环外登记：

```
register_hook("PreToolUse", permission_hook)  # 挂上权限检查
register_hook("PreToolUse", log_hook)         # 挂上日志记录
register_hook("PostToolUse", large_output_hook)
```

以后程序每次走到 `trigger_hooks("PreToolUse", block)`，就会依次调用：

```
permission_hook(block)
    ↓
log_hook(block)
    ↓
执行工具
```

主循环只负责在固定位置触发 Hook，具体功能写在回调里

# MCP

## agent开发中，如果要把现有工具封装成MCP Server，你会怎么做？

如果让我把现有工具封装成 MCP Server，我会先尽量复用原来的业务逻辑，只把 MCP 当成一层适配器。

我会先把现有接口整理成适合 Agent 调用的 Tool，比如 `get_order`、`create_order`，然后为每个 Tool 定义清晰的输入输出 Schema。MCP Server 主要负责参数校验、鉴权、错误处理和调用底层服务，真正的业务逻辑还是放在原来的 Service 层。

另外我会比较关注权限和安全，比如 token、用户身份这些不会让模型直接传，而是在 Server 端处理。对于创建订单、退款这类写操作，我也会考虑幂等，避免 Agent 重试导致重复执行。

如果是本地开发，我会先用 stdio，部署到服务端之后再考虑 HTTP，同时加上日志和 tracing，方便排查 Agent 到底调用了哪个工具、为什么失败。

我理解下来，核心就是让 MCP 层尽量薄，把现有能力标准化成 Agent 更容易理解和调用的接口。

## MCP和Tool call有什么区别？

简单来说：

**Tool Call 是“模型怎么调用工具”**，而 **MCP 是“工具怎么被标准化地提供给模型/Agent”**。

比如模型需要查天气：

```
LLM
 ↓
tool call: get_weather(city="Bangkok")
 ↓
天气工具
```

这里的 **tool call** 是一次具体的调用行为。

而 MCP 更像一套统一协议：

```
LLM / Agent
   ↓
MCP Client
   ↓
MCP Server
   ↓
get_weather / search_order / query_db
```

MCP Server 会告诉 Agent：

- 有哪些工具
- 每个工具是干什么的
- 输入参数是什么
- 返回结果是什么

所以可以理解成：

> **Tool Call 是调用机制，MCP 是工具接入和管理的标准协议。**

两者也不是互斥的。实际 Agent 里经常是：

```
MCP Server 暴露 Tool
        ↓
Agent 发现 Tool
        ↓
LLM 决定使用它
        ↓
发起 Tool Call
```

如果是面试里，我会一句话回答：**“Tool Call 解决模型调用某个函数的问题，MCP 解决不同工具如何用统一协议接入 Agent 生态的问题。”**


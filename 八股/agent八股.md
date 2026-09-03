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


# FastAPI

- **异步性能高**
- **开发效率高**
- **自动生成文档**

Pydantic做类型校验

## 路径参数

URL路径的一部分/book/{id}；指向唯一的，特定的资源，GET方法。

类型注解Path：

- ... 表示必填
- gt大于，lt小于，description
- maxlength，minlength

## 查询参数

声明的参数不是路径参数时候，路径操作函数会把该参数自动解释为查询参数。位置：URL?之后。

类型注解Query，和Path类似。

## 请求体参数

位置：HTTP请求的消息体（body）中。作用：创建、更让新资源，携带大量数据，如JSON。方法：POST，PUT

![78911671283](C:\Users\Administrator\Desktop\study\八股\images\1789116712833.png)

类型注解：pydantic里的Field函数

## 响应类型

默认情况下，FastAPI 会自动将路径操作函数返回的Python 对象(字典、列表、Pydantic模型等)，经由jsonable_encoder 转换为JSON兼容格式，并包装为 JSONResponse返回。这省去了手动序列化的步骤，让开发者能更专注于业务逻辑。

还支持非JSON数据（如HTML，文件流）。

## 异常相应处理

用 fastapi 里的 HTTPExceptio(status_code= ,detauk=" ")

## 中间件

中间件是一个在**每次请求进入FastAPI应用时**都会被执行的函数。

中间件：函数的顶部使用装饰器@app.middleware("http")

FastAPI代码从上到下注册的中间件，执行顺序是：请求阶段从上到下，响应阶段从下到上，俗称「洋葱模型」，**最后注册的中间件，是最外层，请求时最先执行。**

```
@app.middleware("http")
async def m1(request, call_next):
    print("m1 请求")
    resp = await call_next(request)
    print("m1 响应")
    return resp
```

## 依赖注入

通过依赖注入系统来共享通用逻辑，减少代码重复。

为啥不用中间件？	中间件是控制所有请求，依赖注入是自定义范围的。

创建依赖项--->导入Depends--->声明依赖项

![78913113795](C:\Users\Administrator\Desktop\study\八股\images\1789131137952.png)

## ORM

ORM（Object-RelationalMapping，对象关系映射）是一种编程技术，用于在面向对象编程语言和关系型数据库之间建立映射。允许开发者通过操作对象的方式与数据库进行交互，无需直接编写复杂的SQL语句。

SQLAlchemy
from fastapi import FastAPI, Path, Query, Depends
from pydantic import BaseModel,Field

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello FastAPI"}


@app.get("/hello/{name}")
async def hello(name: str):
    return {"message": f"Hello, {name}"}

@app.get("/book/{id}")
async def book(id: int = Path(...)):
    return {"id":id, "message": f"Book {id}"}





@app.middleware("http")
async def m1(request, call_next):
    print("m1 请求")
    resp = await call_next(request)
    print("m1 响应")
    return resp

@app.middleware("http")
async def m2(request, call_next):
    print("m2 请求")
    resp = await call_next(request)
    print("m2 响应")
    return resp

async def common_parameters(
        skip: int = Query(0,ge=0),
        limit: int =Query(10,le =60),
):
    return{"skip":skip, "limit":limit}

#
@app.get("/news/news_list")
async def get_news_list(common = Depends(common_parameters)):
    return common

@app.get("/user/user_list")
async def get_user_list(common = Depends(common_parameters)):
    return common
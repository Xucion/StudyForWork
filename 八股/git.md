## mac

mac系统的命令行工具包（包含git）

xcode-select --install

## git与github的绑定

先看当前配置：

```
git config --global user.name
git config --global user.email
```

如果没有，设置

```
git config --global user.name "Xucion"
git config --global user.email "xxx@163.com"
```

我现在的配置：

```
PS C:\Users\Administrator\Desktop\study> git config --global --list
safe.directory=*
user.name=Xucion
user.email=2628282145@qq.com
http.proxy=http://127.0.0.1:7890
http.postbuffer=1048576000
https.proxy=http://127.0.0.1:7890
credential.https://gitee.com.provider=generic
gui.recentrepo=D:/mqtt多车监控平台v1.1
```

### 推荐方式SSH绑定GitHub

```
你的电脑
   ↓
生成 SSH Key
   ↓
私钥留在电脑
公钥上传 GitHub
   ↓
GitHub 识别你的电脑
```

ls ~/.ssh

若没有就生成

- ssh-keygen -t ed25519 -C "github邮箱"

- 接下来：

  ```
  Enter passphrase
  ```

  这是问你要不要给私钥设置密码。

  学习环境下你可以直接回车。

  公司电脑的话，可以设置一个 passphrase，安全性更好。

  可以查看公钥 cat ~/.ssh/id_ed25519.pub

### SSH Key加到GitHub

打开Github

- 进入：

- ```
  头像
  ↓
  Settings
  ↓
  SSH and GPG keys
  ↓
  New SSH key
  ```

- Title 随便写，例如：

- ```
  My MacBook
  ```

- 或者：

- ```
  Work Laptop
  ```

- Key type 一般保持：

- ```
  Authentication Key
  ```

- Key 里面粘贴刚才：

- ```
  cat ~/.ssh/id_ed25519.pub
  ```

- 得到的整行内容。

- 然后：

- ```
  Add SSH key
  ```

### 可以clone仓库了

- 选择SSH，得到

- ```
  git@github.com:username/project.git
  ```

- 然后：

- ```
  git clone git@github.com:username/project.git
  ```

- 例如：

- ```
  git clone git@github.com:Xucion/sky-take-out-ai-agent.git
  ```



## git check out xxx

签出（分离）：只适合查看代码，不适合修改代码，因为detached意思是分离头指针，此时不属于任何分支

|      | discard                  | reset                          | revert                       |
| ---- | ------------------------ | ------------------------------ | ---------------------------- |
| 行为 | 放弃还没commit的文件更改 | 把仓库强制回退到某个历史状态   | 生成反向commit抵消某次commit |
| 场景 | 未提交                   | 单人使用的分支还未提交远端仓库 | 多人协作的分支               |


### Github 使用笔记  


​    
​    
### 1 Github hosts  

由于国内网络封闭原因，在国内访问 Github 较慢或者无法访问，可通过修改 Hosts 方式改善 Github 网站访问效率  

定期更新的 Github hosts 库:  

[https://github.com/ineo6/hosts](https://github.com/ineo6/hosts "https://github.com/ineo6/hosts")  

[https://gitee.com/ineo6/hosts](https://gitee.com/ineo6/hosts "https://gitee.com/ineo6/hosts")  



### 2 Github 终端代理 

#### 2.1 http 代理

适用于 git 终端通过http/https 方式与远程仓库进行交互  

当使用命令行进行代码提交时，若出现连接不上 github 的问题，可以考虑使用代理(前提是有网络代理)  

抛出异常:  

```
Failed to connect to github.com port 443: Timed out
```

添加全局代理:  

```
git config --global http.proxy http://proxy_ip:proxy_port
git config --global https.proxy http://proxy_ip:proxy_port
```

除了使用 http 代理外，也可以使用 socket 代理，socket 代理链接为:  

```
socks5://proxy_ip:proxy_port
```

针对特定网站(github.com)添加全局代理:  

```
git config --global http.https://github.com.proxy http://proxy_ip:proxy_port
git config --global https.https//github.com.proxy http://proxy_ip:proxy_port
```

如若取消代理，删除即可  

```
git config --global --unset http.proxy
git config --global --unset https.proxy
```

#### 2.2 ssh 代理

适用于 git 终端通过 ssh 方式与远程仓库进行交互  

在 `.ssh` 目录下新建 `config` 文件  

`.ssh` 目录路径  

```
Windows: C:\Users\Username\.ssh
Linux/macOS: ~/.ssh
```

`config` 文件内容:  

```yaml
# 这里的 -a none 是 NO-AUTH 模式，参见 https://bitbucket.org/gotoh/connect/wiki/Home 中的 More detail 一节
ProxyCommand connect -S 127.0.0.1:1080 -a none %h %p

Host github.com
  User git
  Port 22
  Hostname github.com
  # 注意修改路径为你的路径
  IdentityFile "C:\Users\Doraeming\.ssh\id_rsa"
  TCPKeepAlive yes

Host ssh.github.com
  User git
  Port 443
  Hostname ssh.github.com
  # 注意修改路径为你的路径
  IdentityFile "C:\Users\Doraeming\.ssh\id_rsa"
  TCPKeepAlive yes
```

**注意更换私钥路径**  

推荐参考文档:  

[Windows 下 Git SSH 连接方式配置 Socks 代理](https://upupming.site/2019/05/09/git-ssh-socks-proxy)  


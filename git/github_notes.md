### Github 使用笔记  


​    
​    
### 1 Github hosts  

由于国内网络封闭原因，在国内访问 Github 较慢或者无法访问，可通过修改 Hosts 方式改善 Github 网站访问效率  

定期更新的 Github hosts 库:  

[https://github.com/ineo6/hosts](https://github.com/ineo6/hosts "https://github.com/ineo6/hosts")  

[https://gitee.com/ineo6/hosts](https://gitee.com/ineo6/hosts "https://gitee.com/ineo6/hosts")  



### 2 Github 终端代理  

当使用命令行进行代码提交时，若出现连接不上 github 的问题，可以考虑使用代理(前提是有网络代理)  

抛出异常:  

```
Failed to connect to github.com port 443: Timed out
```

添加代理:  

```
git config --global http.proxy http://proxy_ip:proxy_port
git config --global https.proxy http://proxy_ip:proxy_port
```

如若取消代理，删除即可  

```
git config --global unset http.proxy
git config --global unset https.proxy
```




## Linux 杂记(CentOS7)  




### 1 时间同步  

```bash
## 安装时间同步工具 ntpdate
yum install -y ntpdate
## 从网络服务器同步时间 
ntpdate -u ntp.aliyun.com
```

更多:  

[CentOS 7同步时间的2种方法](https://www.xiaoz.me/archives/12989 "https://www.xiaoz.me/archives/12989")  

### 2 查看 yum 已经安装的软件  

```bash
## 查看yum 安装的所有软件
yum list installed
## 查看指定安装的软件
yum list installed | grep xxx
```

更多:  

[CentOS下如何使用yum查看安装过的软件包](https://blog.csdn.net/don_chiang709/article/details/91571424 "https://blog.csdn.net/don_chiang709/article/details/91571424")  






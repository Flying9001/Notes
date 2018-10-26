## macOS 开发工具  


​    
​    
​    
### 1 命令行工具-brew  

brew 官网(包含安装教程): [https://brew.sh/](https://brew.sh/ "https://brew.sh/")  



### 2 版本管理工具-git    

#### 2.1 安装  

mac 下 `brew` 安装 `git` 命令: `brew install git`  

#### 2.2 使用技巧  

[在Mac、Linux 终端显示 Git 当前所在分支](https://gist.github.com/yisibl/8281454 "https://gist.github.com/yisibl/8281454")  

[Mac: mac git 的安装 及实现自动补全](https://blog.csdn.net/DinnerHowe/article/details/79838444 "https://blog.csdn.net/DinnerHowe/article/details/79838444")



### 3 mac 安装第三方软件  

教程:  

[如何打开 macOS Sierra 允许“任何来源”选项，运行第三方应用](https://www.jianshu.com/p/d16060951236 "https://www.jianshu.com/p/d16060951236")  

[安装包损坏，打不开怎么处理？打不开“XXX”,因为它来自身份不明的开发者怎么处理？](http://mac.orsoon.com/news/187368.html "http://mac.orsoon.com/news/187368.html")  



### 4 Mac 安装 JDK  

[MAC安装JDK及环境变量配置](https://blog.csdn.net/vvv_110/article/details/72897142 "https://blog.csdn.net/vvv_110/article/details/72897142")  



### 5 Mac 安装 Java IDE -- InttelliJ IDEA  

教程: [Mac版Intelli IDEA 2018.2.2永久破解（到2100年1月）——2018.09.04亲测](https://blog.csdn.net/qq_32732581/article/details/82381271 "https://blog.csdn.net/qq_32732581/article/details/82381271")  



### 6 Mac 安装 MySQL 及其管理工具 Navicat Preminum  

#### 6.1 Mac 使用 brew 安装 MySQL  

教程: [https://segmentfault.com/q/1010000000475470](https://segmentfault.com/q/1010000000475470 "https://segmentfault.com/q/1010000000475470")  

操作步骤:  

6.1.1 安装: `brew install mysql`  

6.1.2 启动 mysql: `mysql.server`  

6.1.3 执行 mysql 初始化脚本(mysql 自带): `/usr/local/opt/mysql/bin/mysql_secure_installation`  

根据脚本提示进行设置即可  

6.1.4 MySQL 常用命令  

启动: `mysql.server stop`  

停止: `mysql.server stop`  

查看状态: `mysql.server status`  

(使用 `root` 账户)登陆 MySQL(必须在 MySQL启动的情况下): `mysql -u root -p`  



#### 6.2 Mac 版 Navicat Preminum 的安装与使用 

下载(版本12.022):   

链接: [https://pan.baidu.com/s/13jcisKkQfESikJExhR7bsw](https://pan.baidu.com/s/13jcisKkQfESikJExhR7bsw "https://pan.baidu.com/s/13jcisKkQfESikJExhR7bsw") 提取码: `vrga`  

激活教程:  

[https://github.com/DoubleLabyrinth/navicat-keygen](https://github.com/DoubleLabyrinth/navicat-keygen "https://github.com/DoubleLabyrinth/navicat-keygen")  

首次使用 `Navicat` 连接 MySQL 会抛出以下错误:  

```bash
- Authentication plugin 'caching_sha2_password' cannot be loaded
```

解决办法:  

使用终端应用(terminal.app)登陆 MySQL,然后执行以下命令:  

```bash
ALTER USER 'username'@'ip_address' IDENTIFIED WITH mysql_native_password BY 'password';
```

`username`: 待添加的用户名  

`ip_address`: 连接数据库的 ip 地址,如果是本机连接,则可以填 `localhost` 或者 `127.0.0.1`   

`password`:  需要设定的密码  









  













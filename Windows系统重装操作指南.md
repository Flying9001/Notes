# Windows 系统重装操作指南  


### 1.优盘启动盘制作  

优盘启动制作软件/PE系统制作: [http://www.wepe.com.cn/download.html](http://www.wepe.com.cn/download.html "http://www.wepe.com.cn/download.html")  
    
​    
### 2.系统镜像以及驱动下载  

系统镜像下载地址(原装系统): [https://msdn.itellyou.cn/](https://msdn.itellyou.cn/ "https://msdn.itellyou.cn/")  
驱动精灵装机版/离线版: [http://www.drivergenius.com/zhuangji.asp](http://www.drivergenius.com/zhuangji.asp "http://www.drivergenius.com/zhuangji.asp")  
**系统镜像与离线驱动软件需要保存在启动优盘中**  
    
​    
### 3.优盘启动并安装系统  

微PE优盘系统安装操作指南: [用微PE在UEFI+GPT模式下安装WIN7、WIN10教程（已修正）](https://tieba.baidu.com/p/4445983090 "https://tieba.baidu.com/p/4445983090")  
    
注意事项: **win7 必须使用 MBR 磁盘分区格式,win10 建议使用 GPT 分区格式**  
关于 MBR 与 GPT 区别卡参考:  
[UEFI+GPT与BIOS+MBR各自有什么优缺点](https://www.zhihu.com/question/28471913 "https://www.zhihu.com/question/28471913")  
[EFI、UEFI、MBR、GPT的区别](https://blog.csdn.net/mao0514/article/details/51162915 "https://blog.csdn.net/mao0514/article/details/51162915")  
    
​    
### 4.驱动安装  

根据系统版本运行步骤2中的离线版驱动软件,完成网卡驱动的安装(有了网卡去重才能上网,为后边的系统激活与软件安装做准备)  
    
​    
### 5.系统激活  

#### 5.1 win7 系统激活  

(**如果软件提前下载,激活过程无需联网**)  
工具下载(WIN7Activation.exe):  
MEGA 网盘: [https://mega.nz/#!KbYk2CKC!3Jg1qHicp5K1T6gevXM3GQnUX2uAn4PWj_p7wnMg-Gk](https://mega.nz/#!KbYk2CKC!3Jg1qHicp5K1T6gevXM3GQnUX2uAn4PWj_p7wnMg-Gk "https://mega.nz/#!KbYk2CKC!3Jg1qHicp5K1T6gevXM3GQnUX2uAn4PWj_p7wnMg-Gk")  
百度云网盘: [https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw](https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw "https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw") 密码: 5j87  
    
#### 5.2 win10 激活:  

[win10专业版激活方法](https://blog.csdn.net/AinUser/article/details/79247841 "https://blog.csdn.net/AinUser/article/details/79247841")  
[win10激活密钥](https://blog.csdn.net/timo1160139211/article/details/79308755 "https://blog.csdn.net/timo1160139211/article/details/79308755")  
[Win10激活失败的原因及解决方法大全](http://www.w10zj.com/Win10xy/Win10xf_1381.html "http://www.w10zj.com/Win10xy/Win10xf_1381.html")  
    
win10激活命令(cmd 下执行,**激活过程需要联网**):  
    
- (1) 删除原有产品密钥  
`slmgr.vbs /upk`  
  
- (2) 安装激活码  
`slmgr /ipk W269N-WFGWX-YVC9B-4J6C9-T83GX`  
  
- (3) 设置密钥管理服务  
`slmgr /skms zh.us.to`  
  
- (4) 验证激活  
`slmgr /ato`  
  
#### 5.3 windows 万能激活工具  

- Windows 万能激活工具: 包括 windows 系统,Office  
下载:  
[http://www.microsofttoolkitofficial.info/](http://www.microsofttoolkitofficial.info/ "http://www.microsofttoolkitofficial.info/")  
[http://freemicrosofttoolkit.com](http://freemicrosofttoolkit.com "http://freemicrosofttoolkit.com")  
  
### 6.系统设置  

参考: [与windows的斗智斗勇](https://github.com/Flying9001/Notes/blob/master/Tools/%E4%B8%8EWindows%E7%9A%84%E6%96%97%E6%99%BA%E6%96%97%E5%8B%87.md "https://github.com/Flying9001/Notes/blob/master/Tools/%E4%B8%8EWindows%E7%9A%84%E6%96%97%E6%99%BA%E6%96%97%E5%8B%87.md")  

### 7.常用软件安装  

#### 7.1 解压软件  

Bandizip:  
下载:  [https://www.bandisoft.com/bandizip/](https://www.bandisoft.com/bandizip/ "https://www.bandisoft.com/bandizip/")  
    
#### 7.2 完整驱动安装  

驱动精灵免安装版(dirvergeniusGreen.zip):  
PC6下载: [驱动精灵绿色版 v9.61.412.1420单文件纯净版](http://www.pc6.com/softview/SoftView_120008.html "http://www.pc6.com/softview/SoftView_120008.html")  
MEGA网盘: [https://mega.nz/#!7LhyAYAQ!WjBS3_BUWTtmnwpUDDbNH9NyPrgUJ_z1XNh2p0tVYIM](https://mega.nz/#!7LhyAYAQ!WjBS3_BUWTtmnwpUDDbNH9NyPrgUJ_z1XNh2p0tVYIM)  
百度云网盘: [https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw](https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw "https://pan.baidu.com/s/1QQWgbDSwdzsb0Y-cD3rkiw") 密码: 5j87  
    
#### 7.3 远程控制  

TeamViewer:  
下载: [https://www.teamviewer.com/zhcn/download/windows/](https://www.teamviewer.com/zhcn/download/windows/ "https://www.teamviewer.com/zhcn/download/windows/")    
    
#### 7.4 安全软件  

火绒安全  
下载: [https://www.huorong.cn/](https://www.huorong.cn/ "https://www.huorong.cn/")  
    
#### 7.5 办公软件(office)  

Office:  
下载: [https://msdn.itellyou.cn/](https://msdn.itellyou.cn/ "https://msdn.itellyou.cn/")  
激活:   
[http://www.officezhushou.com/office/office2010pojie.html](http://www.officezhushou.com/office/office2010pojie.html "http://www.officezhushou.com/office/office2010pojie.html")  
[http://www.jb51.net/softs/74332.html](http://www.jb51.net/softs/74332.html "http://www.jb51.net/softs/74332.html")  
可参考 5.3 ,使用 windows 万能激活工具  
    
#### 7.6 代理软件  

ShadowSockets  
下载: [https://github.com/shadowsocks/shadowsocks-windows/releases](https://github.com/shadowsocks/shadowsocks-windows/releases "https://github.com/shadowsocks/shadowsocks-windows/releases")  
    
#### 7.7 浏览器  

- 7.7.1 Chrome  
官网下载(需要饭蔷):[https://www.google.com/chrome/](https://www.google.com/chrome/ "https://www.google.com/chrome/")  
「PCOnline」下载: [http://dl.pconline.com.cn/download/51614.html](http://dl.pconline.com.cn/download/51614.html "http://dl.pconline.com.cn/download/51614.html")  
「下载之家」下载: [http://www.downza.cn/soft/26885.html](http://www.downza.cn/soft/26885.html "http://www.downza.cn/soft/26885.html")  
  
- 7.7.2 FireFox  
英文版下载: [https://www.mozilla.org/en-US/firefox/](https://www.mozilla.org/en-US/firefox/ "https://www.mozilla.org/en-US/firefox/")  
中文版下载: [https://www.mozilla.org/zh-CN/firefox/](https://www.mozilla.org/zh-CN/firefox/ "https://www.mozilla.org/zh-CN/firefox/")  
  
#### 7.8 聊天软件  

- 7.8.1 TIM  
相当于企业版QQ  
下载: [https://tim.qq.com/download.html](https://tim.qq.com/download.html "https://tim.qq.com/download.html")     
  
- 7.8.2 微信   
下载: [https://weixin.qq.com/](https://weixin.qq.com/ "https://weixin.qq.com/")  
  
#### 7.9 输入法  

- 7.9.1 搜狗输入法   
下载: [https://pinyin.sogou.com/](https://pinyin.sogou.com/ "https://pinyin.sogou.com/")  
  
- 7.9.2 QQ输入法  
下载: [http://qq.pinyin.cn/](http://qq.pinyin.cn/ "http://qq.pinyin.cn/")  
  
#### 7.10 邮箱  

- 7.10.1 Foxmail:  
下载: [http://www.foxmail.com/](http://www.foxmail.com/ "http://www.foxmail.com/")  
  
#### 7.11 资源管理器  

- 7.11.1 Clover  
一款像浏览器一样使用的 Windows 资源管理器  
(会有广告,可在防火墙中关闭联网权限)    
下载: [http://cn.ejie.me/](http://cn.ejie.me/ "http://cn.ejie.me/")  
  
#### 7.12 垃圾清理工具  

- 7.12.1 CCleaner:  
Pro版官网下载: [https://www.ccleaner.com/ccleaner/download/professional](https://www.ccleaner.com/ccleaner/download/professional "https://www.ccleaner.com/ccleaner/download/professional")  
绿色免安装下载1: [https://pan.baidu.com/share/init?surl=i5x21Rf](https://pan.baidu.com/share/init?surl=i5x21Rf "https://pan.baidu.com/share/init?surl=i5x21Rf"),密码: etdw  
绿色免安装下载2: [https://pan.lanzou.com/b22558/](https://pan.lanzou.com/b22558/)  
CCleaner Professional 注册码  
**断网**以后在需要注册的地方输入以下即可,后期更新需要**手动更新**:  
Name : Registered User  
License Key : CBB4-FJN4-EPC6-G5P6-QT4C  
  
#### 7.13 下载工具  

- 7.13.1 迅雷精简版  
官网下载: [http://dl.xunlei.com/mini.html](http://dl.xunlei.com/mini.html "http://dl.xunlei.com/mini.html")  
网盘下载: MiniThunder*: [https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ](https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ "https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ") 密码: c3pn  
网盘下载: [https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U](https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U "https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U")    
  
- 7.13.2 IDM(InternetDownloadManager)  
v6.30 绿色破解版下载1: [https://pan.lanzou.com/b22253/](https://pan.lanzou.com/b22253/ "https://pan.lanzou.com/b22253/")  
v6.30 绿色破解版下载2: [https://pan.baidu.com/share/init?surl=jHYIOf0](https://pan.baidu.com/share/init?surl=jHYIOf0 "https://pan.baidu.com/share/init?surl=jHYIOf0") ,密码: nuq5  
v6.30 绿色破解版下载3: [https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs](https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs "https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs")  

#### 7.14 播放器  

- 7.14.1 Media Player Classic - BE  
  本地音视频播放器,基于 "Media Player Classic"  
  下载: [https://sourceforge.net/projects/mpcbe/](https://sourceforge.net/projects/mpcbe/ "https://sourceforge.net/projects/mpcbe/")  
- 7.14.2 网易云音乐  
  下载: [https://music.163.com/#/download](https://music.163.com/#/download "https://music.163.com/#/download")  
- 7.14.3 Listen1  
  One for all free music in China   
  (全平台 安装版/插件 支持)  
  下载:  [https://listen1.github.io/listen1/](https://listen1.github.io/listen1/ "https://listen1.github.io/listen1/")  
#### 7.15 图片编辑器  

- 7.15.1 光影魔术手  
  操作简单,效果专业图片编辑器  
  下载: [http://www.neoimaging.cn/](http://www.neoimaging.cn/ "http://www.neoimaging.cn/")  
#### 7.16 PDF 阅读器   

- 7.16.1 AdobeReader  

  下载1: [https://mega.nz/#!jKYnVA6A!Ounb3QkUtLbOzcPIbuMstnsnKPYWJLcFijtxC1pMjuk](https://mega.nz/#!jKYnVA6A!Ounb3QkUtLbOzcPIbuMstnsnKPYWJLcFijtxC1pMjuk "https://mega.nz/#!jKYnVA6A!Ounb3QkUtLbOzcPIbuMstnsnKPYWJLcFijtxC1pMjuk")  

  官网下载(有捆绑): [https://adobe-reader.en.softonic.com/](https://adobe-reader.en.softonic.com/ "https://adobe-reader.en.softonic.com/")  



---

## 以下为高级进阶部分,非 开发者/搞机者 勿入  
---


### 8. 编程软件安装  

#### 8.1 编辑器  

- 8.1.1 Sublime Text 3  
  下载: [https://www.sublimetext.com/](https://www.sublimetext.com/ "https://www.sublimetext.com/")  
  使用教程: [https://github.com/Flying9001/Notes](https://github.com/Flying9001/Notes "https://github.com/Flying9001/Notes")  
  
- 8.1.2 Notepad++  
  下载: [https://notepad-plus-plus.org/](https://notepad-plus-plus.org/ "https://notepad-plus-plus.org/")  
  
- 8.1.3 Typora  
可能是 windows 平台最好的 Markdown 编辑器  
下载: [https://typora.io/](https://typora.io/ "https://typora.io/")  
  
- 8.1.4 Yu Writer  
Markdown Editer,类似 Typora，支持源码+预览  
下载: [https://ivarptr.github.io/yu-writer.site/](https://ivarptr.github.io/yu-writer.site/ "https://ivarptr.github.io/yu-writer.site/")  
  
    
#### 8.2 JDK(Java Development Kit)  

- JDK  
下载: [http://www.oracle.com/technetwork/java/javase/overview/index.html](http://www.oracle.com/technetwork/java/javase/overview/index.html "http://www.oracle.com/technetwork/java/javase/overview/index.html")  
环境配置:   
Windows:  
[Windows环境下JDK安装与环境变量配置详细的图文教程](http://www.cnblogs.com/liuhongfeng/p/4177568.html "http://www.cnblogs.com/liuhongfeng/p/4177568.html")  
[Win10下 Java环境变量配置](https://www.cnblogs.com/cnwutianhao/p/5487758.html "https://www.cnblogs.com/cnwutianhao/p/5487758.html")    
Linux:  
[linux环境jdk安装及配置](https://blog.csdn.net/licongcong_0224/article/details/12756959 "https://blog.csdn.net/licongcong_0224/article/details/12756959")  
[JDK 安装](http://wiki.jikexueyuan.com/project/linux-in-eye-of-java/JDK-Install.html "http://wiki.jikexueyuan.com/project/linux-in-eye-of-java/JDK-Install.html")  
[Linux安装jdk的三种方法](http://blog.51cto.com/vvxyz/1642258 "http://blog.51cto.com/vvxyz/1642258")  
  
#### 8.3 Maven  

- Maven  
  下载: [https://maven.apache.org/index.html](https://maven.apache.org/index.html "https://maven.apache.org/index.html")  
  环境配置:   
  [Maven - 环境配置](http://wiki.jikexueyuan.com/project/maven/environment-setup.html "http://wiki.jikexueyuan.com/project/maven/environment-setup.html")  
  教程:  
  [Maven入门（含实例教程](https://blog.csdn.net/u013142781/article/details/50316383 "https://blog.csdn.net/u013142781/article/details/50316383")  
  Maven 仓库:  
  [http://mvnrepository.com/](http://mvnrepository.com/ "http://mvnrepository.com/")  
#### 8.4 Redis

- 8.4.1 Redis  
  内存数据库,处理高速缓存  
  下载:  
  Windows:   
  [https://github.com/MicrosoftArchive/redis/releases](https://github.com/MicrosoftArchive/redis/releases "https://github.com/MicrosoftArchive/redis/releases")  
  Linux:  
  [https://redis.io/download](https://redis.io/download "https://redis.io/download")  
  安装教程:  
  Windows:   
  [Windows安装redis并将redis设置成服务](https://blog.csdn.net/mrqiang9001/article/details/79428976 "https://blog.csdn.net/mrqiang9001/article/details/79428976")      
  Linux:  
  [centOS 7 安装配置 Redis](https://blog.csdn.net/mrqiang9001/article/details/80212803 "https://blog.csdn.net/mrqiang9001/article/details/80212803")  
  [Linux 平台配置 Redis 的远程连接以及其他基础配置](https://blog.csdn.net/mrqiang9001/article/details/80212884 "https://blog.csdn.net/mrqiang9001/article/details/80212884")  
  [Linux 平台将 Redis 设置为服务并开机自启动](https://blog.csdn.net/mrqiang9001/article/details/80295261 "https://blog.csdn.net/mrqiang9001/article/details/80295261")  
  使用教程:  
  [SDR(spring.data.redis)与Sentinel高可用集群Redis客户端Jedis配置](https://blog.csdn.net/tengxing007/article/details/77219841 "https://blog.csdn.net/tengxing007/article/details/77219841")  
  [基于redis的短信验证码服务开发](https://blog.csdn.net/wenbo20182/article/details/62037747 "https://blog.csdn.net/wenbo20182/article/details/62037747")  
- 8.4.2 Redis Desktop Manager  
  Windows 平台 Redis GUI 管理工具  
  下载: [https://redisdesktop.com/](https://redisdesktop.com/ "https://redisdesktop.com/")  

#### 8.5 Tomcat  

- 8.5.1 Tomcat  

  下载: [http://tomcat.apache.org/index.html](http://tomcat.apache.org/index.html "http://tomcat.apache.org/index.html")  

#### 8.6 网络工具、测试工具  

- 8.6.1 Postman  
模拟用户请求的测试工具  
下载: [https://www.getpostman.com/](https://www.getpostman.com/ "https://www.getpostman.com/")  
  
- 8.6.2 Wireshak  
网络抓包工具,可用于分析本机网络请求  
下载: [https://www.wireshark.org/](https://www.wireshark.org/ "https://www.wireshark.org/")  
依赖: WinPcap  
winPcap 下载: [https://www.winpcap.org/](https://www.winpcap.org/ "https://www.winpcap.org/")  
  
- 8.6.3 ShadowSockets  
自动网络代理工具(**需要有自己的VPS**,可实现**只有被蔷的网站才走代理**)  
详见:  7.6
  
- 8.6.4 Lantern  
免费的代理工具  
(免费版限流)  
官网下载: [https://getlantern.org/en_US/](https://getlantern.org/en_US/ "https://getlantern.org/en_US/")    
「Github」下载: [https://github.com/getlantern/forum](https://github.com/getlantern/forum "https://github.com/getlantern/forum")  

#### 8.7 版本管理  

- 8.7.1 git  
  分布式版本管理工具  
  下载: [https://git-scm.com/downloads](https://git-scm.com/downloads "https://git-scm.com/downloads")  

- 8.7.2 [TortoiseGit](https://tortoisegit.org/)   

  git 辅助管理工具  

  下载: [https://tortoisegit.org/download/](https://tortoisegit.org/download/ "https://tortoisegit.org/download/")  
####  8.8 数据库   

- 8.8.1 MySQL  
下载: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/ "https://dev.mysql.com/downloads/mysql/")  
安装教程(免安装版): [https://blog.csdn.net/mrqiang9001/article/details/78070297](https://blog.csdn.net/mrqiang9001/article/details/78070297 "https://blog.csdn.net/mrqiang9001/article/details/78070297")  
  
- 8.8.2 Navicat  
数据库管理工具,支持(MySQL/Oracle/SQL Server/PostgreSQL/SQLite/MariaDB)  
官网下载: [https://www.navicat.com/en/products#navicat](https://www.navicat.com/en/products#navicat "https://www.navicat.com/en/products#navicat")  
Navicat Preminum 11 下载: [https://mega.nz/#!qDYSUSaR!q_2dOWDRmnrfgkdc5Wtzg5pLw5FZg5_cnek5nQ3quKs](https://mega.nz/#!qDYSUSaR!q_2dOWDRmnrfgkdc5Wtzg5pLw5FZg5_cnek5nQ3quKs)  
Navicat Preminum 11 破解: [https://mega.nz/#!Xf4ilQhL!53cmLVITe9xM1DgWmMEw2VNmAUHN5TFkCc_WDWVTomI](https://mega.nz/#!Xf4ilQhL!53cmLVITe9xM1DgWmMEw2VNmAUHN5TFkCc_WDWVTomI)  
  
- 8.8.3 Power Designer  
**专业**数据库设计工具  
安装文件 下载: [https://pan.baidu.com/share/init?surl=jIIgeZ8](https://pan.baidu.com/share/init?surl=jIIgeZ8 "https://pan.baidu.com/share/init?surl=jIIgeZ8"),密码: `spk4`  
破解文件 下载: [https://pan.baidu.com/share/init?surl=jIIgeZO](https://pan.baidu.com/share/init?surl=jIIgeZO "https://pan.baidu.com/share/init?surl=jIIgeZO"),密码: `24xv`  
汉化文件 下载: [https://pan.baidu.com/share/init?surl=pLA4siv](https://pan.baidu.com/share/init?surl=pLA4siv "https://pan.baidu.com/share/init?surl=pLA4siv"),密码: `eaji`  
安装教程: [PowerDesigner安装教程（含下载+汉化+破解）](https://www.fujieace.com/software/powerdesigner.html "https://www.fujieace.com/software/powerdesigner.html")  
  
### 8.9 Java IDE(集成开发环境)  

- 8.9.1 Eclipse  
下载: [https://www.eclipse.org/downloads/packages/](https://www.eclipse.org/downloads/packages/ "https://www.eclipse.org/downloads/packages/")  
主题下载: [http://www.eclipsecolorthemes.org/](http://www.eclipsecolorthemes.org/ "http://www.eclipsecolorthemes.org/")  
  
- 8.9.2 Intellij IDEA  
下载: [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/ "https://www.jetbrains.com/idea/")  
破解教程: [IntelliJ IDEA（2017）安装和破解](https://www.jianshu.com/p/ad3830095fb3 "https://www.jianshu.com/p/ad3830095fb3")  
破解文件: [http://idea.lanyus.com/](http://idea.lanyus.com/)  
配置文件:  
`%intellij install home%\bin\idea.exe.vmoptions`  
`%intellij install home%\bin\idea64.exe.vmoptions`  
注册代码(Active code):  
```json
ThisCrackLicenseId-{  
"licenseId":"ThisCrackLicenseId",  
"licenseeName":"idea",  
"assigneeName":"",  
"assigneeEmail":"idea@163.com",  
"licenseRestriction":"For This Crack, Only Test! Please support genuine!!!",  
"checkConcurrentUse":false,  
"products":[  
{"code":"II","paidUpTo":"2099-12-31"},  
{"code":"DM","paidUpTo":"2099-12-31"},  
{"code":"AC","paidUpTo":"2099-12-31"},  
{"code":"RS0","paidUpTo":"2099-12-31"},  
{"code":"WS","paidUpTo":"2099-12-31"},  
{"code":"DPN","paidUpTo":"2099-12-31"},  
{"code":"RC","paidUpTo":"2099-12-31"},  
{"code":"PS","paidUpTo":"2099-12-31"},  
{"code":"DC","paidUpTo":"2099-12-31"},  
{"code":"RM","paidUpTo":"2099-12-31"},  
{"code":"CL","paidUpTo":"2099-12-31"},  
{"code":"PC","paidUpTo":"2099-12-31"}  
],  
"hash":"2911276/0",  
"gracePeriodDays":7,  
"autoProlongated":false}  
```

#### 8.10 SSH 客户端  

- 8.10.1 MobaXterm  
全能的 ssh 客户端,支持建立ssh/telnet/rsh/ftp/sftp/serial/vnc/xdmcp/rdp等多种远程会话，其中xdmcp/vnc可以用于访问远程桌面。也支持本地bash或者cmd  
下载1: [MobaXterm Professional Edition 10.5 Portable](https://downloadly.win/mobaxterm-professional-edition-10-5-portable/ "https://downloadly.win/mobaxterm-professional-edition-10-5-portable/")  
下载2: [MobaXterm Professional 10.5 Cracked Full](http://crackswall.com/mobaxterm-crack-professional/ "http://crackswall.com/mobaxterm-crack-professional/")  
下载3: [MobaXterm Professional(SSH/X远程客户端) v10.5 注册版](https://www.xp510.com/xiazai/Application/other/40006.html "https://www.xp510.com/xiazai/Application/other/40006.html")  
  
### 8.11 虚拟机  

- 8.11.1 VMware Workstation Pro  
虚拟机安装软件  
官网下载: [https://my.vmware.com/web/vmware/downloads](https://my.vmware.com/web/vmware/downloads "https://my.vmware.com/web/vmware/downloads")  
v14版 下载: [VMware Pro v14.1.1 官方版本及激活密钥](http://www.zdfans.com/5928.html "http://www.zdfans.com/5928.html")  
VMware 2017 v14.x 永久许可证激活密钥:  
`FF31K-AHZD1-H8ETZ-8WWEZ-WUUVA`  
`CV7T2-6WY5Q-48EWP-ZXY7X-QGUWD`  
  
---
## 能让你的 Windows 飞起来的软件工具  
---

### 9 系统插件  

#### 9.1 f.lux  
智能护眼软件  
下载: [https://justgetflux.com/](https://justgetflux.com/ "https://justgetflux.com/")  
    
#### 9.2 Everything  
windows 平台快速搜索文件软件  
下载: [https://www.voidtools.com/](https://www.voidtools.com/ "https://www.voidtools.com/")  
    
#### 9.3 Wox  
windows 快速启动程序插件,类似与 macOS 的 SpoitLight,可和 Everything 无缝对接,插件丰富  
下载: [http://www.wox.one/](http://www.wox.one/ "http://www.wox.one/")  
    
#### 9.4 Seer  
类似于 macOS 平台按「空格」进行文件预览的工具  
(本文链接中的版本免费,最新版收费)  
下载: [https://sourceforge.net/projects/ccseer/](https://sourceforge.net/projects/ccseer/ "https://sourceforge.net/projects/ccseer/")  
    
#### 9.5 TranslucentTB  
Windows 平台状态栏工具,可将状态栏设置为磨砂/全透明状态  
下载: [https://github.com/TranslucentTB/TranslucentTB](https://github.com/TranslucentTB/TranslucentTB "https://github.com/TranslucentTB/TranslucentTB")  
    
#### 9.5 ScreenToGif  
录屏转为 gif, gif 制作  
下载: [https://github.com/NickeManarin/ScreenToGif](https://github.com/NickeManarin/ScreenToGif)  

​    







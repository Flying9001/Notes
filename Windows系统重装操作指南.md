# Windows 系统重装操作指南  
    
    
### 1.优盘启动盘制作  
    
优盘启动字作软件/PE系统制作: [http://www.wepe.com.cn/download.html](http://www.wepe.com.cn/download.html "http://www.wepe.com.cn/download.html")  
    
    
### 2.系统镜像以及驱动下载  
    
系统镜像下载地址(原装系统): [https://msdn.itellyou.cn/](https://msdn.itellyou.cn/ "https://msdn.itellyou.cn/")  
驱动精灵装机版/离线版: [http://www.drivergenius.com/zhuangji.asp](http://www.drivergenius.com/zhuangji.asp "http://www.drivergenius.com/zhuangji.asp")  
**系统镜像与离线驱动软件需要保存在启动优盘中**  
    
    
### 3.优盘启动并安装系统  
    
微PE优盘系统安装操作指南: [用微PE在UEFI+GPT模式下安装WIN7、WIN10教程（已修正）](https://tieba.baidu.com/p/4445983090 "https://tieba.baidu.com/p/4445983090")  
    
注意事项: **win7 必须使用 MBR 磁盘分区格式,win10 建议使用 GPT 分区格式**  
关于 MBR 与 GPT 区别卡参考:  
[UEFI+GPT与BIOS+MBR各自有什么优缺点](https://www.zhihu.com/question/28471913 "https://www.zhihu.com/question/28471913")  
[EFI、UEFI、MBR、GPT的区别](https://blog.csdn.net/mao0514/article/details/51162915 "https://blog.csdn.net/mao0514/article/details/51162915")  
    
    
### 4.驱动安装  
    
根据系统版本运行步骤2中的离线版驱动软件,完成网卡驱动的安装(有了网卡去重才能上网,为后边的系统激活与软件安装做准备)  
    
    
### 5.系统激活  
    
#### 5.1 win7 系统激活(**如果软件提前下载,激活过程无需联网**)  
    
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
    
    
### 6.系统设置  
    
#### 6.1 关闭系统更新服务  
    
6.1.1 Win + R --- 输入 `service.mse`进入「服务」设置 --- 在服务列表中找到 `Windows update` 服务,右键「属性」,进入属性设置,「停止」服务，并设置为「手动」  
    
6.1.2 鼠标右键任务栏->任务管理器->服务(在上边)->服务(右下角)->名称->Windows Update->停止->属性->禁用  
    
6.1.3 命令行关闭：cmd->sc config "wuauserv" start=disable   //将windows update状态设置为禁用  
   sc stop wuauserv    //关闭windows update服务  
    
#### 6.2 卸载无关应用  
    
  6.2.1 Win + R --- 输入 `control`进入「控制面板」  --- 选择「卸载程序」  --- 卸载无关紧要的程序  
    
  6.2.2 关于 win10: 点击「开始」菜单 --- 进入「设置」 --- 选择「应用」 --- 卸载无关紧要的应用程序  
    
    
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
    
火绒安全:  
下载: [https://www.huorong.cn/](https://www.huorong.cn/ "https://www.huorong.cn/")  
    
#### 7.5 办公软件(office)  
    
Office:  
下载: [https://msdn.itellyou.cn/](https://msdn.itellyou.cn/ "https://msdn.itellyou.cn/")  
激活:   
[http://www.officezhushou.com/office/office2010pojie.html](http://www.officezhushou.com/office/office2010pojie.html "http://www.officezhushou.com/office/office2010pojie.html")  
[http://www.jb51.net/softs/74332.html](http://www.jb51.net/softs/74332.html "http://www.jb51.net/softs/74332.html")  
    
#### 7.6 代理软件  
    
ShadowSockets:  
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
    
- 7.8.1 TIM(相当于企业版QQ):  
下载: [https://tim.qq.com/download.html](https://tim.qq.com/download.html "https://tim.qq.com/download.html")     
    
- 7.8.2 微信:   
下载: [https://weixin.qq.com/](https://weixin.qq.com/ "https://weixin.qq.com/")  
    
#### 7.9 输入法  

- 7.9.1 搜狗输入法:   
下载: [https://pinyin.sogou.com/](https://pinyin.sogou.com/ "https://pinyin.sogou.com/")  
    
- 7.9.2 QQ输入法:  
下载: [http://qq.pinyin.cn/](http://qq.pinyin.cn/ "http://qq.pinyin.cn/")  
    
#### 7.10 邮箱  
    
- Foxmail:  
下载: [http://www.foxmail.com/](http://www.foxmail.com/ "http://www.foxmail.com/")  
    
#### 7.11 资源管理器  
    
Clover: 一款像浏览器一样使用的 Windows 资源管理器
会有广告,可在防火墙中关闭联网权限    
下载: [http://cn.ejie.me/](http://cn.ejie.me/ "http://cn.ejie.me/")  
    
#### 7.12 垃圾清理工具  
    
- CCleaner:  
Pro版官网下载: [https://www.ccleaner.com/ccleaner/download/professional](https://www.ccleaner.com/ccleaner/download/professional "https://www.ccleaner.com/ccleaner/download/professional")  
绿色免安装「百度云」下载1: [https://pan.baidu.com/share/init?surl=i5x21Rf](https://pan.baidu.com/share/init?surl=i5x21Rf "https://pan.baidu.com/share/init?surl=i5x21Rf"),密码: etdw  
绿色免安装下载2: [https://pan.lanzou.com/b22558/](https://pan.lanzou.com/b22558/)  
CCleaner Professional 注册码  
**断网**以后在需要注册的地方输入以下即可,后期更新需要**手动更新**:  
Name : Registered User  
License Key : CBB4-FJN4-EPC6-G5P6-QT4C  
    
#### 7.13 下载工具  
    
- 7.13.1 迅雷精简版:  
官网下载: [http://dl.xunlei.com/mini.html](http://dl.xunlei.com/mini.html "http://dl.xunlei.com/mini.html")  
「百度云」网盘下载: MiniThunder*: [https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ](https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ "https://pan.baidu.com/s/1LkNMnsJUTz4V6mDVsbEQtQ") 密码: c3pn  
「MEGA」网盘下载: [https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U](https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U "https://mega.nz/#!rS4k2RyS!05IZ90rFVRWr5cClKUriSp6ID3F054ae5pCk9955L_U")    
    
- 7.13.2 IDM(InternetDownloadManager)  
v6.30 绿色破解版下载1: [https://pan.lanzou.com/b22253/](https://pan.lanzou.com/b22253/ "https://pan.lanzou.com/b22253/")  
v6.30 绿色破解版下载2: [https://pan.baidu.com/share/init?surl=jHYIOf0](https://pan.baidu.com/share/init?surl=jHYIOf0 "https://pan.baidu.com/share/init?surl=jHYIOf0") ,密码: nuq5  
v6.30 绿色破解版下载3: [https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs](https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs "https://mega.nz/#!KGBR1BDL!bW_vX4i9cUrf3CETMuZ3lrz6chaUf4jOrGrQGwsOjKs")  

#### 7.14 播放器  
    
- 7.14.1 Media Player Classic - BE: 本地音视频播放器,基于 "Media Player Classic"  
下载: [https://sourceforge.net/projects/mpcbe/](https://sourceforge.net/projects/mpcbe/ "https://sourceforge.net/projects/mpcbe/")  
    
- 7.14.2 网易云音乐  
下载: [https://music.163.com/#/download](https://music.163.com/#/download "https://music.163.com/#/download")  
    
- 7.14.3 Listen1: One for all free music in China (全平台 安装版/插件 支持)  
下载:  [https://listen1.github.io/listen1/](https://listen1.github.io/listen1/ "https://listen1.github.io/listen1/")  
    
    
### 8. 编程软件安装  
    
#### 8.1 编辑器  
    
- 8.1.1 Sublime Text 3  
下载: [https://www.sublimetext.com/](https://www.sublimetext.com/ "https://www.sublimetext.com/")  
使用教程: [https://github.com/Flying9001/Notes](https://github.com/Flying9001/Notes "https://github.com/Flying9001/Notes")  
    
- 8.1.2 Notepad++  
下载: [https://notepad-plus-plus.org/](https://notepad-plus-plus.org/ "https://notepad-plus-plus.org/")  
    
- 8.1.3 Yu Writer: Markdown Editer  
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
    
#### 8.4 网络工具、测试工具  
    
- 8.4.1 Postman: 模拟用户请求的测试工具  
下载: [https://www.getpostman.com/](https://www.getpostman.com/ "https://www.getpostman.com/")  
    
- 8.4.2 Wireshak: 网络抓包工具,可用于分析本机网络请求  
下载: [https://www.wireshark.org/](https://www.wireshark.org/ "https://www.wireshark.org/")  
依赖: WinPcap  
winPcap 下载: [https://www.winpcap.org/](https://www.winpcap.org/ "https://www.winpcap.org/")  
    
- 8.4.3 ShadowSockets: 自动网络代理工具(**需要有自己的VPS**,可实现**只有被蔷的网站才走代理**)  
详见: 
#### 7.6
    
- 8.4.4 Lantern: 免费的代理工具(免费版限流)  
官网下载: [https://getlantern.org/en_US/](https://getlantern.org/en_US/ "https://getlantern.org/en_US/")    
「Github」下载: [https://github.com/getlantern/forum](https://github.com/getlantern/forum "https://github.com/getlantern/forum")  

#### 8.5 Redis  
    
- Redis: 内存数据库,处理高速缓存  
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






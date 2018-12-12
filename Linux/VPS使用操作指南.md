## VPS 使用操作指南  


​    
​
### 1 什么是 VPS 
>虚拟专用服务器（英语：Virtual private server，缩写为 VPS），是将一台服务器分割成多个虚拟专享服务器的服务。实现VPS的技术分为容器技术和虚拟机技术 。在容器或虚拟机中，每个VPS都可分配独立公网IP地址、独立操作系统、实现不同VPS间磁盘空间、内存、CPU资源、进程和系统配置的隔离，为用户和应用程序模拟出“独占”使用计算资源的体验。VPS可以像独立服务器一样，重装操作系统，安装程序，单独重启服务器。
> 来源: 维基百科

### 2 VPS 可以做什么  

对于个人而言,VPS 可以用来搭建个人网站,当然对于国内用户而言,也可以用来构建可-学-伤-王的T-子  



### 3 VPS 选购  

可参考  

[2018年最好的国外VPS推荐 —— 购买指南](https://www.banwagonghost.net/2018_nian_zui_hao_de_guo_wai_vps_tui_jian_gou_mai_zhi_nan/ "https://www.banwagonghost.net/2018_nian_zui_hao_de_guo_wai_vps_tui_jian_gou_mai_zhi_nan/")    



### 4 防火墙设置  

centOS 7 开始使用 firewalld 代替了先前的iptables 作为防火墙的设置  

以下以 centOS 7 为例:  

[firewall-cmd](https://wangchujiang.com/linux-command/c/firewall-cmd.html "https://wangchujiang.com/linux-command/c/firewall-cmd.html")  

[CentOS 7 中firewall-cmd命令](https://www.jianshu.com/p/411274f96492 "https://www.jianshu.com/p/411274f96492")  

[Centos7.3防火墙配置](https://www.cnblogs.com/xxoome/p/7115614.html "https://www.cnblogs.com/xxoome/p/7115614.html")  



### 5 ssh 端口更改  

[CentOS7设置ssh服务以及端口修改](https://blog.csdn.net/Mrqiang9001/article/details/78308830 "https://blog.csdn.net/Mrqiang9001/article/details/78308830")  

更换端口之后需要在防火墙中关闭旧的 `ssh` 端口  

关闭端口命令:  

```bash
firewall-cmd --zone=public --remove-port=22/tcp --permanent
```



### 6 安装代理工具  

目前(2018年)流行的代理工具有 ShadowSocket系列以及v2ray,推荐使用 v2ray  

v2ray 是比 shadowSocket **更快更安全**的代理工具  

原因:    

[新一代科学上网利器：V2Ray扫盲教程 ](http://blog.whiterabbitxyj.com/2018/08/31/V2Ray/ "http://blog.whiterabbitxyj.com/2018/08/31/V2Ray/")  

[传说中的性能测试](https://steemit.com/cn/@v2ray/3cjiux "https://steemit.com/cn/@v2ray/3cjiux")    



#### 6.1 V2ray 服务端配置    

- 安装 `wget` 工具  

```bash
yum -y install wget
```

- 安装解压工具 `unzip`  

```bash
yum install zip unzip
```

- 下载 v2ray 自动安装脚本  

```bash
wget https://install.direct/go.sh
```

- 修改脚本权限  

```bash
chmod 700 go.sh
```

- 执行自动安装脚本  

```bash
bash ./go.sh
```

脚本执行完成之后,v2ray就已经安装完成,但是**并没有启动服务**  

V2ray 服务常用命令:  

```bash
## 启动
systemctl start v2ray

## 停止
systemctl stop v2ray

## 重启
systemctl restart v2ray
```

详细配置可参考教程:  

[V2Ray完全使用教程](https://yuan.ga/v2ray-complete-tutorial/ "https://yuan.ga/v2ray-complete-tutorial/")    



#### 6.2 v2ray 客户端配置  

v2ray 本身是不区分客服端和服务端的,核心代码是没有 GUI 操作工具的,但是有基于 v2ray 核心框架的第三方 GUI 操作工具  

windows 系统:  

[https://github.com/2dust/v2rayN](https://github.com/2dust/v2rayN "https://github.com/2dust/v2rayN")  

macOS 系统:  

[https://github.com/Cenmrev/V2RayX](https://github.com/Cenmrev/V2RayX "https://github.com/Cenmrev/V2RayX")  

Android 系统:  

[https://github.com/2dust/v2rayNG](https://github.com/2dust/v2rayNG "https://github.com/2dust/v2rayNG")  



### 7 手动添加代理规则  

客户端可以选择使用 `pac` 自动代理模式,但是由于某些网站并没有即时添加到公共 `pac` 规则中,因此需要手动添加代理规则,具体教程可参考:  

[浅析PAC，教你动手修改你的PAC文件及user-rule文件实现自动代理](https://www.cnblogs.com/edward2013/p/5560836.html "https://www.cnblogs.com/edward2013/p/5560836.html")  

**该教程适用于 Shadowsocket 系列以及 V2ray**  

推荐使用手动创建 `user-rule.txt ` 文件,将其放到和 `pac.txt` 同一个目录中,重启服务即可  





### X 常见问题    

[搬瓦工能 ping 通但是 SSH 无法连接的原因和解决方法](https://www.bandwagonhost.net/1990.html "https://www.bandwagonhost.net/1990.html")      

[搬瓦工换IP方法总结：免费换IP / 付费换IP方法](https://www.bandwagonhost.net/1983.html "https://www.bandwagonhost.net/1983.html")    

[2018 最新检查搬瓦工 IP / 端口是否被封的方法](https://www.bandwagonhost.net/1934.html "https://www.bandwagonhost.net/1934.html")  
    
​    
​    
​    
​    
​    
​    
—— update 2018-12-12  















## 使用 centOS 7 注意事项(踩过的坑)  


​    
​    
### 1 centOS 7 与 centOS 6 区别  

后台开发常用到的区别:   

- 1 加入了 `systemctl` 这一神器  
- 2 防火墙设置不同  
- 3 加入了更高级别的安全设置(SELinux)  

centOS 7 与 centOS 6 区别可参考:  

[Linux学习总结（29）——CentOS 6和CentOS 7区别总结](<https://blog.csdn.net/u012562943/article/details/71107137> "<https://blog.csdn.net/u012562943/article/details/71107137>")  

[【Linux】CentOS6与CentOS7区别](<https://www.jianshu.com/p/69aa9b2fc213> "<https://www.jianshu.com/p/69aa9b2fc213>")  

[CentOS7与CentOS6区别](<https://blog.csdn.net/xu_Melon/article/details/79043898> "<https://blog.csdn.net/xu_Melon/article/details/79043898>")  

​    

### 2 防火墙设置  

centOS 7 开始防火墙使用 `firewalld` 代替了 `iptables`  

**firewalld** 简单使用:  

开机启动 | 开机不启动 | 启动 | 停止 | 重启 | 查询状态 `firewalld` 防火墙服务  

```bash
sudo systemctl enable|disable|start|stop|restart|status firewalld
```

永久(`--permanent`) 开放(`--zone=public`) 某一个端口:  

```bash
sudo firewall-cmd --add-port=666/tcp --zone=public --permanent
```

同时永久(`--permanent`) 开放(`--zone=public`) 多个(包括连续的和不连续的)端口:  

```bash
sudo firewall-cmd --add-port=667/udp --add-port=888-890/tcp --zone=public --permanent
```

```bash
sudo firewall-cmd --add-port={123,456,789}/tcp --zone=public --permanent
```

永久(`--permanent`) 移除(`remove`) 一个或多个开放的(`--zone=public`)端口:  

```bash
firewall-cmd --remove-port=6666/tcp --zone=public --permanent
```

查询开放的端口:  

```bash
firewall-cmd --list-port
```

刷新/重新载入 防火墙:  

```
sudo firewall-cmd --reload
```

在每一次更新防火墙规则(包括更新端口、添加屏蔽 ip 等操作) 之后都必须重新载入才会生效  

关于 **firewalld** 参考资料:  

[What is the difference between iptables and uncomplicated firewall(UFW) in Linux?](https://www.quora.com/What-is-the-difference-between-iptables-and-uncomplicated-firewall-UFW-in-Linux "https://www.quora.com/What-is-the-difference-between-iptables-and-uncomplicated-firewall-UFW-in-Linux")  

[IPTABLES VS FIREWALLD](https://www.unixmen.com/iptables-vs-firewalld/ "https://www.unixmen.com/iptables-vs-firewalld/")  

[How to Set Up a Firewall with FirewallD on CentOS 7](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-firewall-using-firewalld-on-centos-7 "https://www.digitalocean.com/community/tutorials/how-to-set-up-a-firewall-using-firewalld-on-centos-7")  

​    

### 3 引入 SELinux  

什么是 **SELinux** ? 其全名是: **Security Enhanced Linux**,简单来讲就是更高级别的安全设置(这玩意儿的戏特别多,一不小心就会被它坑了)  

有些时候,当你以为使用 `firewalld` 开放了防火墙端口就万事大吉的时候,说不定你的服务就被这玩意给拦截了  

`SELinux` 端口管理简单使用:  

安装 `SELinux` 管理工具 `semanage` :  

```bash
yum install policycoreutils-python
```

查看某一项服务(如 `http`) 相关的端口:  

```bash
semanage port -l | grep http
```

向 `SELinux` 中添加一项服务(如 `xxx` 服务)的开放端口

```bash
semanage port -a -t xxx_port_t -p tcp 2333
```

将 `SELinux` 中的端口移除:  

```bash
semanage port --delete -t http_port_t -p tcp 8899-8902
```

重启 `SELinux` 服务:  

```
systemctl restart sshd.service
```

关于 **SELinux** 参考资料:  

[An Introduction to SELinux on CentOS 7](https://www.digitalocean.com/community/tutorials/an-introduction-to-selinux-on-centos-7-part-1-basic-concepts "https://www.digitalocean.com/community/tutorials/an-introduction-to-selinux-on-centos-7-part-1-basic-concepts")  

[How to Disable SELinux on CentOS 7](https://linuxize.com/post/how-to-disable-selinux-on-centos-7/ "https://linuxize.com/post/how-to-disable-selinux-on-centos-7/")  

[CentOS 7 在SELinux保护下安全打开端口](https://blog.csdn.net/caoshiying/article/details/74925514 "https://blog.csdn.net/caoshiying/article/details/74925514")  

[Use SELinux Port Labeling To Allow Services To Use Non-Standard Ports](https://www.rootusers.com/use-selinux-port-labeling-to-allow-services-to-use-non-standard-ports/ "https://www.rootusers.com/use-selinux-port-labeling-to-allow-services-to-use-non-standard-ports/")  

​    

### 4 Nginx 端口转发 502  

你绝对想不到当你按照 Nginx 官网教程来安装使用 Nginx 的时候，竟然也会报错，而罪魁祸首就是 **SELinux**  

关于 **Nginx** 参考资料:  

[官方文档 Installing NGINX Open Source](https://docs.nginx.com/nginx/admin-guide/installing-nginx/installing-nginx-open-source/ "https://docs.nginx.com/nginx/admin-guide/installing-nginx/installing-nginx-open-source/")  

[官方文档 Controlling NGINX Processes at Runtime](https://docs.nginx.com/nginx/admin-guide/basic-functionality/runtime-control/ "https://docs.nginx.com/nginx/admin-guide/basic-functionality/runtime-control/")  

[官方文档 Creating NGINX Plus and NGINX Configuration Files](https://docs.nginx.com/nginx/admin-guide/basic-functionality/managing-configuration-files/ "https://docs.nginx.com/nginx/admin-guide/basic-functionality/managing-configuration-files/")  

[How to Remove / Uninstall Nginx on CentOS 7 / RHEL 7 / Oracle Linux 7](https://webhostinggeeks.com/howto/how-to-remove-uninstall-nginx-on-centos-7-rhel-7-oracle-linux-7/ "https://webhostinggeeks.com/howto/how-to-remove-uninstall-nginx-on-centos-7-rhel-7-oracle-linux-7/")  

[Linux下使用Nginx端口转发出现502错误的一种解决办法](https://blog.csdn.net/only_yu_yy/article/details/78825151 "https://blog.csdn.net/only_yu_yy/article/details/78825151")  

[CentOS 7中nginx反向代理因为selinux出现错误的解决办法](https://my.oschina.net/songxinqiang/blog/636241 "https://my.oschina.net/songxinqiang/blog/636241")  

快速解决办法  

```
setsebool -P httpd_can_network_connect 1
```



### 5 使用 systemctl 命令实现服务开机启动  

**systemctl** 这一命令工具堪称神器,设置开机启动只是其功能的一项牛刀小试  

思路:  

- 1 将某一个程序(包含程序运行参数)设置为服务  
- 2 执行 `systemctl enable xxx.service` 即可实现开机启动  
- 3 执行 `systemctl disable xxx.service` 关闭开机启动  
- 4 如果某一个程序安装之后本身就会注册成为服务(如mysql 安装之后其服务名称为 `mysqld`),则跳过第 1 步  



### 6 yum 常用命令  

从 yum 库安装软件  

```
yum install <software_name>
```

安装本地 rpm 软件  

```
yum localinstall /path/to/rpm/file
```

列出所有可更新软件  

```
yum check-update
```

更新所有 yum 安装的软件  

```
yum update
```

更新指定软件  

```
yum update <software_name>
```

列出所有可安装软件  

```
yum list
```

查询 yum 库中某个可安装软件  

```
yum list | grep <keyword>
```

查询某个已安装软件  

```
yum list installed | grep <keyword>
```

删除/卸载软件  

```
yum remove <software_name>
```

从 yum 库查找软件  

```
yum search <keyword>
```

清除所有缓存  

```
yum clean all
```

创建缓存  

```
yum makecache
```

参考资料:  

[Linux yum 命令-菜鸟教程](https://www.runoob.com/linux/linux-yum.html "https://www.runoob.com/linux/linux-yum.html")  

​    

### 7 将 yum 源替换为国内阿里云镜像  

备份默认 yum 源  

```
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
```

使用 aliyun 的 yum 源  

```
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
```

清除缓存  

```
yum clean all
```

重建缓存  

```
yum makecache
```

参考资料:  

[修改CentOS默认yum源为国内yum镜像源](https://cloud.tencent.com/developer/article/1173087 "https://cloud.tencent.com/developer/article/1173087")  



### 8 设置交换分区 swap

swap 直译过来是交换，swap 的作用是当物理内存不足时，使用使用磁盘作为补充内存，这就相当于扩大了内存容量，从而避免系统因为内存不足而直接崩溃。因此 swap 也可以成为虚拟内存，或者交换内存。  

swap 设置指令如下  

检查是否设置 swap  

```bash
swapon -s
```

如果什么也没有显示就表明未设置 swap  

创建 swap 分区  

```bash
sudo fallocate -l 1G /swapfile
```

这里 1G 为 swap 分区大小，建议设置为物理内存大小的 50%  

设置 swap 分区文件读写权限  

```bash
sudo chmod 600 /swapfile
```

将文件设置为 swap 文件  

```bash
sudo mkswap /swapfile
```

（临时）启用 swap 文件，系统重启后会失效  

```bash
sudo swapon /swapfile
```

永久性启用 swap 文件  

编辑 `/etc/fstab` 文件  

```bash
vim /etc/fstab
```

在最后一行添加参数  

```
/swapfile   swap    swap    sw  0   0
```

设置Swappiness 参数，该参数含义为物理内存还剩多少时开始使用 swap 交换内存  

编辑 `/etc/sysctl.conf` 文件  

```bash
vim /etc/sysctl.conf
```

在最后一行添加参数  

```bash
vm.swappiness=70
```

重启或者执行以下命令使配置生效  

```bash
sudo sysctl -p
```






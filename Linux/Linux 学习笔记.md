## Linux 学习笔记  

<pre><code>
1. 使用普通账户来管理 Linux 主机的资源,当需要系统功能修改的时候，再使用 root 账户  

2. Linux 为多人多工的系统,在关机之前可下查询系统在线者(who)或查询网络服务(netstat -a)
    使用正确的关机方式，可以使用 shutdown 命令

3. 使用 man/info 命令查询命令帮助文档

4. chgrp/chown/chmod  修改文档所属群组/拥有者/权限

5. 文档权限: r(读)=4 w(写)=2 x(执行)=1 rwx=4+2+1=7 ，权限顺序 owner group others
    文档 x 权限是可执行,目录 x 权限是可进入

6. Linux 文档/目录名长度限制 255bytes

</code></pre>


## Linux 文件管理  

<pre><code>  
1. 使用普通账户来管理 Linux 主机的资源,当需要系统功能修改的时候，再使用 root 账户 
2. Linux 为多人多工的系统,在关机之前可下查询系统在线者(who)或查询网络服务(netstat -a)
    使用正确的关机方式，可以使用 shutdown 命令
3. 使用 man/info 命令查询命令帮助文档
4. chgrp/chown/chmod  修改文档所属群组/拥有者/权限
5. 文档权限: r(读)=4 w(写)=2 x(执行)=1 rwx=4+2+1=7 ，权限顺序 owner group others
    文档 x 权限是可执行,目录 x 权限是可进入
6. Linux 文档/目录名长度限制 255bytes
7. 文件,目录相关操作命令
    cd dir-name: 切换目录
    pwd : 显示当前目录
    mkdir dir-name: 创建目录 eg: mkdir test1
    rmdir dir-name : 删除空目录 eg: rmdir test1
    touch file-name : 新建文件
    ls [option]: 列出当前目录下的文件和目录
        常用：
        ls -a : 全部文档,包括隐藏文档 
        ls -l : 显示文档的属性(权限,所属,大小,时间等)
        ls -t : 依照时间顺序
        ls -r : 反向输出
    cp [option] source(源文件/目录) target(目标文件/目录) : 复制  eg : cp test1/note.txt test2/
        常用:
        -a : 相当于 -dr 全部的意思
        -d : 复制对象为链接(link file),则复制链接,而非文档本身
        -i : 如果目标文档已经存在,则在覆盖之前有提示
        -p : 联通文档的属性(权限,用户,时间)一起复制过去
        -r : 递归持续复制,用于目录的复制,复制目录,连同目录内所有
    rm [option] file/dir 删除
        -f 强制删除
        -i 删除前提示
        -r 递归删除(用于删除目录)
    mv [option] source(源文件/目录) target(目标文件/目录)   移动/更名 文件/目录
        -f 强制移动 
        -i 文件覆盖提醒
        -u 如果目标文件已经存在,且 source 比较新,才会更新(update)
8.文件查询
    whereis [option] file/dir : 查询文档/目录
        -l : 列出 whereis 查询的目录以及查询结果
        -b : 只找 binary 格式的文档(二进制文件)
        -m : 只找在说明文档 manual 路径下的文档
        -s : 只找 source 目录下文档
        -u : 搜索不在以上三个目录下的其他特殊文档
    locate [option] file/dir : 查询文档/目录 (locate 是在资料库中查找,资料库会有更新频率,如果是在资料库更新之后创建的文档,则无法搜索到
            可以手动更新资料库 updatedb ,自动更新频率设置: /etc/updatedb.conf )
        -i : 忽略大小写
        -c : 不输出文档名,仅计算找到的文档数量
        -l : 仅输出几行的意思，例如输出五行则是-l 5
        -S ：输出locate 所使用的资料库文档的相关资讯，包括该资料库纪录的文档/目录数量等
        -r ：后面可接正规表示法的显示方式
    find [PATH] [option] file/dir : 查询文档/目录
        参数说明:
		9.1 与时间有关的选项：共有-atime, -ctime 与-mtime ，以-mtime 说明
		   -mtime n ：n 为数字，意义为在n 天之前的『一天之内』被更动过内容的文档；
		   -mtime +n ：列出在n 天之前(不含n 天本身)被更动过内容的文档；
		   -mtime -n ：列出在n 天之内(含n 天本身)被更动过内容的文档。
		   -newer file ：file 为一个存在的文档，列出比file 还要新的文档
		9.2 与使用者或群组名称有关的参数：
		   -uid n ：n 为数字，这个数字是使用者的帐号ID，亦即UID ，这个UID 是记录在
		            /etc/passwd 里面与帐号名称对应的数字。
		   -gid n ：n 为数字，这个数字是群组名称的ID，亦即GID，这个GID 记录在
		            /etc/group
		   -user name ：name 为使用者帐号名称
		   -group name：name 为群组名称
		   -nouser ：寻找文档的拥有者不存在/etc/passwd 的文档
		   -nogroup ：寻找文档的拥有群组不存在于/etc/group 的文档！
		9.3 与文档权限及名称有关的参数：
		   -name filename：搜索文档名称为filename 的文档；
		   -size [+-]SIZE：搜索比SIZE 还要大(+)或小(-)的文档。这个SIZE 的规格有：
		                   c: 代表byte， k: 代表1024bytes。
		   -type TYPE ：搜索文档的类型为TYPE 的，类型主要有：一般正规文档(f), 装置文档(b, c),
		                   目录(d), 连结档(l), socket (s), 及FIFO (p) 等属性。
		   -perm mode ：搜索文档权限值为 mode 的文档 
		   -perm -mode ：搜索全部包含 mode 权限的文档
		   -perm /mode ：搜索包含 mode 中任一权限的文档
		9.4 额外可进行的动作：
		   -exec command ：command 为其他指令，-exec 后面可再接额外的指令来处理搜索到的结果。
		   -print ：将结果打印到屏幕上  

<pre><code>




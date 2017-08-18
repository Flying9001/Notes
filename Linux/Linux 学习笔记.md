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
8. 文档查看命令
    cat [option] finame-name : 从第一行显示文档内容
        -n : 显示行号(包括空白行)
    tac file-name : 从最后一行开始显示文档(反向显示)
    nl [option] file-name : 有行号的显示文档(类似 cat -n ,但是不给空白行编号)
    maore file-name : 从第一行开始一页一页的查看文档
        space(空格键): 向下翻一页
        Enter: 向下一行
        /String : 在显示的内容中查找 "String"
        :f : 显示文档名以及当前的行数
        q : 退出
        b (或者 [Ctrl] + b) : 向前翻页,但是只对文档有用,对管线无用
    less filen-ame : 一页一页显示文档,与 more 类似,但是可以向前翻页
        space(空格键) : 向下翻动一页
        [pagedown] : 向下翻动一页
        [pageup] : 向上翻动一页
        /String : 向下搜索 "String"
        ?String : 项上搜索 "String"
        n : 重复前一个搜索(与 「/」/「?」有关)
        N : 反向重复前一个搜索(与 「/」/「?」有关)
        g : 跳转到资料第一行
        G : 跳转到最后一行
        q : 退出
    head [option] file-name : 查看文档前几行(默认 10 行)
        -n : 查看的行数 eg: head -n 20  test.txt 
    tail [option] file-name : 查看文档的后几行(默认 10 行)
        -n : 查看的行数 eg: tail -n 20  test.txt
        -f : 表示持续监测文档后边的数据,只有按 [Ctrl + c] 才能停止 (在监控日志输出的时候常用)
    touch [option] file-name 创建/修改 文档时间
        文档时间: 
        access time(atime): 文档被读取,则会更新这个时间
        status time(stime): 文刚状态(eg: 权限)修改时,则会更新这个时间
        modification time(mtime): 文档内容变更时,则会更新这个时间 
        
        -a : 修改 access time 
        -c : 修改文档时间,若文档不存在,则新建
        -d : 后边可以接欲修订的日期而不用当前的日期
        -m : 仅修改 mtime
        -t : 后边可以接与修订的日期而不用当前的日期,格式: [YYYYMMDDhhmm]
9. 文件查询
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
        -S ：输出locate 所使用的资料库档案的相关资讯，包括该资料库纪录的档案/目录数量等
        -r ：后面可接正规表示法的显示方式
    find [PATH] [option] file/dir : 查询文档/目录
        选项与参数：
		1. 与时间有关的选项：共有-atime, -ctime 与-mtime ，以-mtime 说明
		   -mtime n ：n 为数字，意义为在n 天之前的『一天之内』被更动过内容的档案；
		   -mtime +n ：列出在n 天之前(不含n 天本身)被更动过内容的档案档名；
		   -mtime -n ：列出在n 天之内(含n 天本身)被更动过内容的档案档名。
		   -newer file ：file 为一个存在的档案，列出比file 还要新的档案档名
		2. 与使用者或群组名称有关的参数：
		   -uid n ：n 为数字，这个数字是使用者的帐号ID，亦即UID ，这个UID 是记录在
		            /etc/passwd 里面与帐号名称对应的数字。这方面我们会在第四篇介绍。
		   -gid n ：n 为数字，这个数字是群组名称的ID，亦即GID，这个GID 记录在
		            /etc/group，相关的介绍我们会第四篇说明～
		   -user name ：name 为使用者帐号名称喔！例如dmtsai
		   -group name：name 为群组名称喔，例如users ；
		   -nouser ：寻找档案的拥有者不存在/etc/passwd 的人！
		   -nogroup ：寻找档案的拥有群组不存在于/etc/group 的档案！
		                当你自行安装软体时，很可能该软体的属性当中并没有档案拥有者，
		                这是可能的！在这个时候，就可以使用-nouser 与-nogroup 搜寻。       
		3. 与档案权限及名称有关的参数：
		   -name filename：搜寻档案名称为filename 的档案；
		   -size [+-]SIZE：搜寻比SIZE 还要大(+)或小(-)的档案。这个SIZE 的规格有：
		                   c: 代表byte， k: 代表1024bytes。所以，要找比50KB
		                   还要大的档案，就是『 -size +50k 』
		   -type TYPE ：搜寻档案的类型为TYPE 的，类型主要有：一般正规档案(f), 装置档案(b, c),
		                   目录(d), 连结档(l), socket (s), 及FIFO (p) 等属性。
		   -perm mode ：搜寻档案权限『刚好等于』 mode 的档案，这个mode 为类似chmod
		                 的属性值，举例来说， -rwsr-xr-x 的属性为4755 ！
		   -perm -mode ：搜寻档案权限『必须要全部囊括mode 的权限』的档案，举例来说，
		                 我们要搜寻-rwxr--r-- ，亦即0744 的档案，使用-perm -0744，
		                 当一个档案的权限为-rwsr-xr-x ，亦即4755 时，也会被列出来，
		                 因为-rwsr-xr-x 的属性已经囊括了-rwxr--r-- 的属性了。
		   -perm /mode ：搜寻档案权限『包含任一mode 的权限』的档案，举例来说，我们搜寻
		                 -rwxr-xr-x ，亦即-perm /755 时，但一个档案属性为-rw-------
		                 也会被列出来，因为他有-rw.... 的属性存在！
		4. 额外可进行的动作：
		   -exec command ：command 为其他指令，-exec 后面可再接额外的指令来处理搜寻到的结果。
		   -print ：将结果列印到萤幕上，这个动作是预设动作！















</code></pre>


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

8. 文档查看命令(尤其在日志查询方面很重要)
    cat [option] finame-name : 从第一行显示文档内容
        -n : 显示行号(包括空白行)
        -b 查询前 n 行数据,去除空白行 eg: cat -b 10 text.log 
        cat -n file-name | grep "string" 在 file-name 文件中显示和 "string" 附近的数据(很重要,根据关键字查询日志)
        sed -n '/2014-12-17 16:17:20/,/2014-12-17 16:17:36/p' test.log  (按照日期查询数据) 在 test.log 中查询这两个时间点之间的数据
        grep '2014-12-17 16:17:20' test.log (按照日期查询数据) 在 test.log 中查询这个时间点的日志(通常用于确认这个时间点是否存在)

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
        tail -n 50 -f test.log (前两个命令的组合) 显示 test.log 文件的后 50 行数据，并在文件内容增加后，自动显示新增的文件内容
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

10. 查看磁盘信息/磁盘分割/磁盘挂载
    df [option] : 列出整体磁盘使用量(也可以看到磁盘挂载信息)
        -h : 根据大小使用适当格式显示,eg:kBytes,Mbytes,Gbytes
        -i : 不使用磁盘容量,而是用 inode 数量显示磁盘使用情况
    du [option] file/dir : 显示文件/目录所占用的磁盘大小
        -s : 列出磁盘占用总量,而不列出每个目录占用的磁盘容量
        -h : 根据大小使用适当格式显示,eg:kBytes,Mbytes,Gbytes
    lsblk : 列出本机磁盘容量信息,可以看到磁盘的分区以及各分区大
    parted device-name print : 查看磁盘分割表类型(MBR/GPT)与分割信息 eg: parted /dev/sda print
    gdisk : GPT分区格式的磁盘分区工具
        常用指令: 
        d : 删除一个分区
        n : 添加一个分区
        p : 打印分割表
        q : 不保存退出
        w : 保存并退出
        ? : 菜单
    fdisk : MBR分区格式的磁盘分区工具
        常用命令和 gdisk 相同
    partprobe [option]: 更新磁盘分区表(partprobe 归属 parted 包下,在使用 gdisk/fdisk 将磁盘分区之后,并没有立刻更新分区表,
        需要重启或者使用 partprobe 命令更新分区表)
        -s : 显示磁盘分区信息(不加 s 则不显示)
    mkfs : 格式化磁盘(磁盘分割之后需要对其进行格式化, mkfs:make fliesyatem,即制作文件系统 如:ext2/3/4/xfs/vfat等 ,具体参数可参考网络) 
        eg:mkfs.xfs /dev/sda4  将sda4盘格式化为 xfs 文件系统
    mount : 挂载 (内容较多,具体操作可参考网络)
        挂载注意事项: 
            (1) 同一个文件系统不要重复挂载在不同的挂载点
            (2) 单一目录不要挂载多个文件系统
            (3) 挂载点要为空目录
        eg: mount /dev/sr0 /mnt/cdrom : 将 CD/DVD 挂载到 /mnt/cdrom 目录下(过载DVD可以使用 loop 方式挂载)
    umount [option] dev-name/mount-point: 卸载,通过 设备名/挂载点
        -f : 强制卸载
        -n : 不更新 /etc/mtab 情况下卸载
        eg : umount /mnt/cdrom : 将挂载在 /mnt/cdrom 目录下的设备卸载
    开机挂载设置目录: /etc/fstab /etc/mtab (具体设置参考网络)   
    
11. 文件链接 link (不能跨文件系统(filesystem))
    ln [option] source target : 创建文件链接(hard link 不支持目录)
        -s : 创建的是 symbolic link(相当于 windows 快捷方式),不加 -s 相当于 hard link 		

12. 终端运行结果输出
    一般情况下,终端运行结果输出在屏幕,但是如果输出结果较多,则前边的输出结果就会看不到(在纯命令行的 Linux系统中)
    为解决这个问题,可以将输出结果保存到文件中
    (1) > file-name : 将「本次」运行结果只输出到文件(默认文件会保存在当前工作目录,文件存在则会覆盖)
        eg: ll / > ll.txt
    (2) tee : 可以将「本次」运行结果输出到屏幕同时保存到文件
        tee [option] file-name : 将本次命令运行的结果显示在屏幕,同时输出到文件(默认文件会保存在当前工作目录,文件存在则会覆盖) eg: ll / | tee ll.txt
        -a : 解决上边文件覆盖问题,将数据结果追加到文件后边
            eg: ll / | tee -a ll.txt
    (3) script : 可以将终端的所有输出(包括「多次」的 命令+运行结果)都保存到文件中(默认文件会保存在当前工作目录,文件存在则会覆盖)
        使用方法: 
            开启script: $ script -- 开始记录 -- 结束 script : $ exit 
        说明: 默认会在当前工作目录生成一个 typescript 文件,文件存在会覆盖
        -a : 解决文件覆盖问题,将数据结果追加的文件后边  eg: script -a script.txt
        -f : 刷新输出,实时记录
        -q : 不显示 script 的启动与退出信息(用户不知道在录屏)
        -t : 显示输出的时间
13. Linux 设置 swap 虚拟内存(用途: 防止服务器遇到程序内存占用过高情况,具体设置参考网络)

14. 文件压缩
    14.1 gzip [option] file-name : gzip 解/压缩(gzip 可以打开compress/zip/gzip 等软件压缩的文档, gzip 压缩的文件后缀为 .gz,压缩后会将源文件删除)
        -c : 将压缩的资料输出到屏幕(实测一堆乱码)
        -d : 解压缩
        -t : 检验压缩文件的一致性(用于判断文件是否有误
        -v : 显示压缩比等信息
        -# : # 表示数字,设置压缩等级, 1 最快,压缩比最差, 9 最慢,压缩比最好, 默认为 6 
            eg: gzip -c test.txt  将 test.txt 文件压缩
    14.2 bzip2 [option] file-name : bzip2 解/压缩(bzip2 压缩率高于 gzip, 支持保留源文件)
        -c : 将压缩的资料输出到屏幕(实测报错)
        -d : 解压缩
        -k : 保留源文件压缩
        -v : 显示压缩比等信息
        -# : 同 gzip (实测小文件看不出对比效果)
    14.3 xz [option] file-name : xz 解/压缩
        -c : 将压缩的资料输出到屏幕
        -d : 解压缩
        -k : 保留源文件压缩
        -l : 列出压缩文件的而相关信息
        -v : 显示压缩比等信息
        -# : 同 gzip (实测小文件看不出对比效果)
    14.4 tar [option] target-file source-file : 打包与解/压缩
        -c : 建立打包文件
        -t : 打包时查看打包文件包含的文件信息 
        -x : 解包/解压缩
        -z : 通过 gzip 进行解/压缩(文件名建议设置为 *.tar.gz)
        -j : 通过 bzip2 进行解/压缩(文件名建议设置为 *.tar.bz2)
        -J : 通过 xz 进行解/压缩(文件名建议设置为 *.tar.xz)(-z -j -J 不能同时出现在一串命令行中)
        -v : 将解/压缩过程中处理的文件名显示出来
        -f : 后边要立刻接需要处理的文件名(一般将其放在最后)
        -C : 将压缩文件解压缩到指定目录
            eg: tar -Jcvf /home/test.tar.xz /home/text/  
                tar -Jxvf /home/text.tar.xz

15. 系统备份(xfs文件系统)
    15.1 xfsdump [option] [file/dir] : 使用 xfsdump 对 xfs 文件系统进行备份
        -L : 系统备份的标签(给系统备份打标签,便于记忆)
        -M : 对备份资料的简易说明 
        -l : (L的小写)备份等级,包含 0~9(默认为 0 ,完整备份)
        -f : 后边接备份文件名 
        -I : 列出当前系统备份的信息(存放目录: /var/lib/xfsdump/inventory) 
        注意事项: xfsdump 只能备份已经挂载的文件系统
                  xfsdump 必须使用 root 身份操作
                  xfsdump 只能备份 xfs 文件系统
                  xfsdump 备份的资料只能使用 xfsrestore 解析
                  xfsdump 是根据文件系统的 UUID 来进行备份的(不能备份具有相同 UUID 的文件系统)
            eg: xfsdump -l 0 -L boot_backups -M boot_backups -f /srv/boot.dump /boot 
    15.2 xfsrestore [option] [file/dir] : 使用 xfsrestore 对 xfs 文件系统进行还原
        -L : 系统备份的标签(Session Label)
        -f : 后边接备份文件名
        -I : 查看备份信息(同 xfsdump -I 功能)
        -i : 进入互动模式(执行每一步都有反馈,高级管理员使用,一般不用)
        -s : 还原特定文件/目录 eg: xfsrestore -f /srv/boot.dump -L boot_backups -s test.txt /boot 只将boot.dump备份文件中的 test.txt 文件
            还原到 /boot 目录(前提是 boot.dump 本分文件中要有 test.txt 文件)
        累积还原操作同完整还原: 操作顺序 level0 -- level1 -- level2 ......

16. 其他备份工具
    16.1 dd : dd if="input-file" of="output-file" bs="block-size" count="number"
            if : inpout file,输入文件/挂载的设备
            of : output file,输出文件/挂载的设备
            bs : 每个 block 的大小(默认 512 bytes)
            count : block 的数量
        dd 备份特点: 会将整个文件系统(或整个盘)备份,不区分磁盘内的文件系统,都可以备份/还原,可用于备份 iso 文件
    16.2 cpio [option] >/< [file/frvice] : 备份/还原
           备份参数: 
               -o : 将资料 copy 输出到文件/设备
               -B : 将预设的 blocks 可以增加到 5120bytes(预设默认为 512bytes)
               >  : 备份符号
            还原参数:          
                -i : 将资料从备份文件/设备中复制到系统中
                -d : 自动创建目录(使用 cpio 所备份的资料文件不一定会在同一层目录中,一次需要进 cpio 还原时自动创建新目录)
                -u : 自动将新文件覆盖旧文件
                -t : 配合 -i,查看以 cpio 建立的文件/设备的内容
            公共参数: 
                -v : 显示备份/还原过程
                -c : 新的 porttable format 方式存储
            eg: find boot | cpio -ovcB > file/device : (要在 / 目录下)将 /boot 目录下所有文件备份
                cpio -ivcdu < file/device : 还原
                cpio -ivct < file/device : 查看
            cpio 备份特点: (1)不区分绝对路径/相对路径 (2)需要借助其他命令辅助(比如 find),才能创建需要备份的文件
17. 文本编辑器vi/vim
    17.1 一般模式(vi/vim刚打开一个文件时即为一般模式)
        [Ctrl] + [f] : 下一页(相当于[PageDown])
        [Ctrl] + [b] : 上一页(相当于[PageUp])
        0 : 将光标移动到光标所在行的第一列(数字 0)
        $ : 将光标移动到光标所在行的末尾
        G : 将光标移动到文档最后
        gg : 将光标移动到文档开头
        n+[Enter] : n 为数字,将光标向下移动 n 行
        /String : 在文档中向下搜索 "String" 的字符串(区分大小写,下同)
        ?String : 在文档中向上搜索 "String" 的字符串
        n : (字母 n )配合查找 /String ?String 跳转到下一个指定位置
        :n1,n2s/word1 /word2/g : n1 与 n2 为数字,在第 n1 与 n2 列之间查找 word1 这个字符串，并将该字符串替换为 word2       
        :1,$s/word1/word2/g : 从文档第一行到最后一行查找 word1 字符串,并将改字符串替换为 word2
        :1,$s/word1/word2/gc : 从文档第一行到最后一行查找 word1 字符串,并将改字符串替换为 word2,在替换之前提示是否替换
        x : 向后删除一个字符(相当于 [Delete])
        X : 向前删除一个字符(相当于 windows 中的 Backspace)
        dd : 删除光标所在行
        ndd : 删除从光标向下 n 行(包括光标所在行)
        yy : 复制光标所在行
        nyy : 复制从光标开始向下 n 行
        p : 张贴
        u : 撤销,后退
        [Ctrl] + r : 前进
    17.2 编辑模式(文档可插入模式,点击 i 可由一般模式进入编辑模式,按 [Ecs] 退出编辑模式,进入一般模式)
        -i : 进入编辑模式,从光标处插入
    17.3 命令模式(由一般模式按 ":" 进入)
        :q : 退出
        :w : 保存
        :wq : 保存并退出
        :q! : 不保存退出
        :w! : 不保存
        :set nu : 显示行号
        :set nonu : 不显示行号
    17.4 块区选择
        v : 字符选择,游标经过的地方会被选中(游标向下,上一行全被选中,相当于 windows 的 shift)
        V : 行选择,游标经过的行会被选中
        [Ctrl] + v : 块选择,游标经过的地方会被选中(游标向下,只会选中游标包含的部分,类似于多行编辑)
        y : 复制选中
        p : 粘贴选中
        d : 删除选中
    17.5 多文档编辑(1 单窗口)
        :n : 编辑下一个文档
        :N : 编辑上一个文档
        :files : 列出当前 vim 打开的文档
        使用 vim 开启多文档 : vim file1 file2 ... filen
    17.6 多文档编辑(2 多窗口)
        :sp [filename] : 开启多窗口, 后边不接文件,则两个窗口显示同一文件(同步显示);后边接文件,新窗口打开新文件
        [Ctrl] + [W] + [up]/k : 切换到上边的窗口([up] 为向上键,按键方式: 先按 [Ctrl],再按 [w],再按其他,并非同步,下同)
        [Ctrl] + [W] + [down]/j : 切换到下边的窗口
        vim 开启多窗口: 在已经打开的 vim 窗口中,命令模式下 按 :sp [filename] 进入多窗口
    17.7 代码提示
        [Ctrl] + [x] -- [Ctrl] + [n] : 根据文件的内容来提示代码
        [Ctrl] + [x] -- [Ctrl] + [f] : 根据文件名来提示代码(不常用)
        [Ctrl] + [x] -- [Ctrl] + [o] : 根据文件后缀名来提示代码
        
    17.8 vim 环境设置与历史记录
        /etc/vimrc : vim 的系统设置文件(不建议修改,用户自定义可以在自己的 home 目录建立一个 .vimrc)
        ~/.viminfo : 当前用户使用 vim 的历史记录(包括操作的文档,使用的命令等等,文件在用户自己的 home 目录,每个用户的 home 目录不同)
    17.9 vim 其他
        (1) vim 程序被中断,则会生成 .filename.swp 文档,下次代开的时候根据需要进进行操作即可(不用担心突然断电等导致 vim 编辑的文档没有保存)
        (2) vim 中文乱码,设置 Linux 语言编码(支持中文的有big5/utf8),文件位置: /etc/locale.conf

18. shell 
    18.1 bash 
        ~/.bash_history : 使用 bash 的历史记录(在使用者的 home 目录下)
        命令补全 : 在输入一个不完整指令的时候按下 [Table] 键可以补全确定唯一的命令,如果根据输入的不完整指令不能确定唯一指令
                    则可以双击 [Table] 查看所有的可能指令
        设置别名(alias) : alias ll="ls -l" (仅限于本用户本次登录)
        type [option] command : 查看命令是否为 bash 内置命令
            -a : 将所有包含该 command 命令的路径都显示出来,包括 alias 和 PATH 中定义的
        \ : 转译字符(eg: 按下\+_[Enter] 则是换行,而不是执行)
        echo : 打印信息,输出信息 eg: echo $PATH
        变量设定 : name='hahaha' (注意单双引号区别,单引号过滤特殊字符属性;双引号保留特殊字符原有属性) 
        取消变量 : unset name
        取变量 : $name
        PS1变量: 由于设置命令行前边的显示属性,具体设置可参考网络 
                 eg: [ljq@learn ~ 02:03 #82]$
        




    









 
</code></pre>


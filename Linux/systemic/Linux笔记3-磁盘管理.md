## Linux 磁盘管理

<pre><code>
1. 查看磁盘信息/磁盘分割/磁盘挂载
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
    partprobe [option]: 更新磁盘分区表(partprobe 归属 parted 包下,在使用 gdisk/fdisk 将磁盘分区之后,并
                        没有立刻更新分区表,
        需要重启或者使用 partprobe 命令更新分区表)
        -s : 显示磁盘分区信息(不加 s 则不显示)
    mkfs : 格式化磁盘(磁盘分割之后需要对其进行格式化, mkfs:make fliesyatem,即制作文件系统 
           如:ext2/3/4/xfs/vfat等 ,具体参数可参考网络) 
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
    
2. 文件链接 link (不能跨文件系统(filesystem))
    ln [option] source target : 创建文件链接(hard link 不支持目录)
        -s : 创建的是 symbolic link(相当于 windows 快捷方式),不加 -s 相当于 hard link 		



     
</ode></pre>

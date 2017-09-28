## Linux 备份

<pre><code>
1. 系统备份(xfs文件系统)
    1.1 xfsdump [option] [file/dir] : 使用 xfsdump 对 xfs 文件系统进行备份
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
    1.2 xfsrestore [option] [file/dir] : 使用 xfsrestore 对 xfs 文件系统进行还原
        -L : 系统备份的标签(Session Label)
        -f : 后边接备份文件名
        -I : 查看备份信息(同 xfsdump -I 功能)
        -i : 进入互动模式(执行每一步都有反馈,高级管理员使用,一般不用)
        -s : 还原特定文件/目录 eg: xfsrestore -f /srv/boot.dump -L boot_backups -s test.txt /boot 只将boot.dump备份文件中的 test.txt 文件
            还原到 /boot 目录(前提是 boot.dump 本分文件中要有 test.txt 文件)
        累积还原操作同完整还原: 操作顺序 level0 -- level1 -- level2 ......

2. 其他备份工具
    2.1 dd : dd if="input-file" of="output-file" bs="block-size" count="number"
            if : inpout file,输入文件/挂载的设备
            of : output file,输出文件/挂载的设备
            bs : 每个 block 的大小(默认 512 bytes)
            count : block 的数量
        dd 备份特点: 会将整个文件系统(或整个盘)备份,不区分磁盘内的文件系统,都可以备份/还原,可用于备份 iso 文件
    2.2 cpio [option] >/< [file/frvice] : 备份/还原
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

     
</ode></pre>

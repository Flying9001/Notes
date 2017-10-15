## Linux 命令行工具 bash

<pre><code>
bash 
    ~/.bash_history : 使用 bash 的历史记录(在使用者的 home 目录下)
    命令补全 : 在输入一个不完整指令的时候按下 [Table] 键可以补全确定唯一的命令,如果根据输入的不完整指令不能确定
              唯一指令
                则可以双击 [Table] 查看所有的可能指令
    设置别名(alias) : alias ll='ls -l' (仅限于本用户本次登录)
        unalias ll : 删除别名
    type [option] command : 查看命令是否为 bash 内置命令
        -a : 将所有包含该 command 命令的路径都显示出来,包括 alias 和 PATH 中定义的
    \ : 转译字符(eg: 按下\+_[Enter] 则是换行,而不是执行)
    echo : 打印信息,输出信息 eg: echo $PATH
    变量设定 : name='hahaha' (注意单双引号区别,单引号过滤特殊字符属性;双引号保留特殊字符原有属性) 
    取消变量 : unset name
    取变量 : $variable,${variable}  (推荐使用 ${variable},因为在取数组类型变量(array)的时候必须要这样才
            可以取到 eg: ${arr[1]})
    PS1变量: 用于设置命令行前边的显示属性,具体设置可参考网络 
             eg: [ljq@learn ~ 02:03 #82]$
    export variable : 可以让子程序共享父程序的变量(将局部变量/自定义变量变成环境变量/全局变量)
            eg: export : 查看当前共享的变量
            export PS1 : 共享 PS1 这个变量
    变量操作:
        read [option] variable : 读取键盘输入的变量
            -p : 后面接提示语
            -t : 后面接等待的时间(单位: s),超过时间将不会等待
        declare/typeset [option] variable : 声明变量类型
            -a : 将变量声明为数组类型(array)
            -i : 将变量声明为整数类型(integer)
            -p : 列出变量的类型属性以及变量值 eg: declare -p sum  : 列出 sum 变量的属性和变量值
                (eg: $ declare -ir sum="300")
            -r : 将变量声明为只读类型(readonly),该变量不可被更改,也不能unset
            -x : 将变量声明为环境变量,相当于 export
            使用 "+" 可以将相关类型取消 eg: declare +r tmp  : 将 tmp 变量的只读性取消
        ${variable#/*end} : 修改变量,将变量 variable 从左边 / 开始删除到以 end 结束,中间以最短的那部分
            # 从左开始最短的那部分
            ## 从左开始最长的那部分
            * 代表 任何长度的任何字符
                eg: [ljq@learn ~]$ echo ${path}
                        /usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/ljq/.local/bin:/home
                          /ljq/bin
                    [ljq@learn ~]$ echo ${path#/*bin:}
                        /usr/bin:/usr/local/sbin:/usr/sbin:/home/ljq/.local/bin:/home/ljq/bin
        ${variable%:*end} : 修改变量,将变量 variable 从右边 : 开始删除到以 end 结束,中间最短的那一部分
            % 从优开始最短的那一部分
            %% 从优开始最长的那一部分
                eg: [ljq@learn ~]$ echo ${path}
                        /usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/ljq/.local/bin:
                        /home/ljq/bin
                    [ljq@learn ~]$ echo ${path%%:*bin}
                        /usr/local/bin
        ${variable/old_str/new_str} : 将变量 variable 中包含的第一个 old_str 替换为 new_str
        ${variable//old_str/new} : 将变量 variable 中包含 old_str 的部分全部替换为 new_str

    用户限定 : ulimit
        ulimit [option] [value] 限定用户的相关操作(默认作用范围:当前用户本次登录)
            -H : 硬性限制,使用者一定不能超过该限定值(类似于机器所能承受的最大载荷)
            -S : 软限制,使用者超过该值会有警告(类似于机器限定的载荷),一般软限制比硬限制小
            -a : 后边不接参数,列出所有限制额度
            -c : 限制核心文件(core file)的最大容量(核心文件: 程序崩溃时,系统可能会将内存中的文件写入文件,这类
                    文件称为核心文件)
            -f : 限制此 shell 可以建立的最大文件(单位: Kbytes)
            -l : 限制内存的容量
            -t : 限制 CPU 的使用时间(单位: s)
            -u : 单一使用者可以使用程序的最大数量
                eg: ulimit -f 10240 : 限制使用者可以创建的最大文件为 10M
    
    输入命令历史 : history
        history [option] hisfiles 显示当前用户的输入命令的历史
            n : n 为数字,表示显示最近 n 此输入命令
            -c : 将目前的 shell 中所有的 history 内容清空
            -a : 将目前新增的 history 指令保存到 hisflies 中,如果没有指定保存的文件路径(hisfiles),默认保存在 ~/.bash_history  中
            -r : 将 hisfile 的内容督导目前 shell 的 history 内存中
            -w : 将目前的 history 内存中的内容写入 hisfiles 中(会清除原来保存的记录)
            s
    登录界面 
        /etc/motd : 远程登录的欢迎界面(root 可编辑修改)
        /etc/issue : 本机登录的欢迎界面(root 可编辑修改)
    
    shell 命令快捷键:
        Crtl + C : 结束当前进程
        Crtl + D : 输入结束(EOF,eg: 邮件结束的时候)
        Crtl + M : 相当于 Enter 
        Ctrl + S : 暂停屏幕的输出
        Crtl + Q : 恢复屏幕的输出
        Crtl + U : 在提示字符下,删除郑航命令
        Ctrl + Z : 暂停当前命令
    shell 特殊符号
        * : 代表 0个到无数个 任意字符
        ? : 代表 一定有一个 任意字符
        [] : 代表 一定有一个在括号内的字符 eg: [asdf] 表示 一定有一个字符,可能是 a,s,d,f 中的任意一个
        [-] : 代表 在编码顺序内的所有字符 。eg: [0-9] 代表 0 到9 之间的所有数字 [a-z] [A-Z] 类似
        [^] : 代表 不包括括号内的字符,即当前字符不属于括号内提供的字符,eg: [^asd] : 表示一定有一个字符,
              但是不是 a,s,d
                
        # : 表示注释 (常用在脚本或script者程序中)
        \ : 转译字符,指跳过下一个字符(程序中通常有些特殊字符具有其特殊意义,因此在需要输入特定字符的时候用转译字符)
        | : 管线(pipe),分割连个管线命令的界定
        ; : 连续两个指令之间用 ; 隔开
    	~ : 使用这的家(home)目录
	$ : 取变量
	& : 工作控制(job control),将指令在后台运行
	! : 逻辑非 
	/ : Linux/Unix路径分隔符(windows下为 \)
	>,>> : 输出导向, > 为输出到指定文件(源文件存在,则覆盖),>> 也是输出到指定文件(原文件存在,则在后边追加)
	<,<< : 输入导向
	'' : 单引号(引号里边为字符串,特殊字符不再具有其特殊意义,eg:'$',则不在表示取变量)
	"" : 双引号(引号里边为字符串,特殊字符仍具有其特殊意义,$:表示取变量)	  
        `` : 反引号中间为可以先执行的命令(也可以用$())
	() : 小括号中间程序表示在子 shell 中执行
	{} : 大括号中间为命令程序块
        
    Linux 输入/输出
        standard output : 标准输出
	standard error output : 标准错误输出
	

    Linux 排序/统计
        sort [option] [file/stdin]  对 文件/输入信息 进行排序
            -f : 忽略大小写
            -b : 忽略最前边的空白字符
            -M : 以月份的名称进行排序,eg: JAN,DEC等
            -n : 使用纯数字进行排序(默认是使用文字进行排序的 a-z)
            -r : 反向排序
            -u : 去重复,相同的资料仅显示一条
            -t : 分割符号,默认使用 「tab」进行分割
            -k : 以设定区间来进行排序
        
        uniq [option] : 设定排序结果显示选项
            -i : 忽略大小写
            -c : 进行计数
                eg: last | cut -d ' ' -f1 | sort | uniq -c

        wc [option] : 显示文件的统计信息
            -l : 仅列出行
            -w : 仅列出多少字(英文单词)
            -m : 仅列出多少字符
                eg: cat demo.txt | wc  : 表示显示 dmeo.txt 文件的 行数,字数,字符数
    
    Linux 控制输出
        参考「Linux笔记7-终端输出到文件」

    Linux 字符转换
        tr [option] str : 替换文件中某一段字符串(这里替换/删除的是只要包含这个字符串中的所有字符)
            -d : 删除文件中包含 str 字符串
            -s : 替换文件中包含 str 字符串
                eg: cat demo.txt | tr -d '[test]' : 表示删除 demo.txt 中包含 test 的字符串
                    cat demo.txt | tr -s '[test]' '[TEST]' : 表示将 demo.txt 中将 test 的字符串
                    替换为 TEST   
    
    Linux 字符对比
        paste [-d] file1 file2 : 对比两个资料(将两个资料中具有相关性的资料放在一行作为对比)
            -d : 后边接自定义的分割符(默认为 「table」)
            - : 如果 file 部分直接写 - ,表示来自标准输入的文件(stardand input)
                eg: paste /etc/passwd /etc/shadow 

    Linux 文件分割
        split [option] file PREFIX : 将文件进行分割
            -b : 后边可以接将要分割成的文件大小,可以加单位,eg: b,k,m,g等
            -l : 以行数进行分割
            PREFIX : 表示前缀,可以作为分割文件的前缀
                eg: split -b 10k demo.log demos : 表示将 demo.log 文件以 10k 为单位进行分割,分割的文件
                    前缀为 demos
                    cat demos* >> demo.log.bak : 表示将之前分割的文件合成一个文件 demo.log.bak 


  
</ode></pre>

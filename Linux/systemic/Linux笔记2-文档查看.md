## Linux 的文档查看命令-日志查看工具

<pre><code>
1. cat [option] finame-name : 从第一行显示文档内容
    -n : 显示行号(包括空白行)
    -b 查询前 n 行数据,去除空白行 eg: cat -b 10 text.log 
    cat -n file-name | grep "string" 在 file-name 文件中显示和 "string" 附近的数据(很重要,根据关键字查询日志)
    sed -n '/2014-12-17 16:17:20/,/2014-12-17 16:17:36/p' test.log  (按照日期查询数据) 在 test.log 中查询这两个时间点之间的数据
    grep '2014-12-17 16:17:20' test.log (按照日期查询数据) 在 test.log 中查询这个时间点的日志(通常用于确认这个时间点是否存在)
2. tac file-name : 从最后一行开始显示文档(反向显示)

3. nl [option] file-name : 有行号的显示文档(类似 cat -n ,但是不给空白行编号)  

4. maore file-name : 从第一行开始一页一页的查看文档
    space(空格键): 向下翻一页
    Enter: 向下一行
    /String : 在显示的内容中查找 "String"
    :f : 显示文档名以及当前的行数
    q : 退出
    b (或者 [Ctrl] + b) : 向前翻页,但是只对文档有用,对管线无用

5. less filen-ame : 一页一页显示文档,与 more 类似,但是可以向前翻页
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

6. head [option] file-name : 查看文档前几行(默认 10 行)
    -n : 查看的行数 eg: head -n 20  test.txt   

7. tail [option] file-name : 查看文档的后几行(默认 10 行)
    -n : 查看的行数 eg: tail -n 20  test.txt
    -f : 表示持续监测文档后边的数据,只有按 [Ctrl + c] 才能停止 (在监控日志输出的时候常用)
    tail -n 50 -f test.log (前两个命令的组合) 显示 test.log 文件的后 50 行数据，并在文件内容增加后，自动显示新增的文件内容
8. touch [option] file-name 创建/修改 文档时间
    文档时间: 
    access time(atime): 文档被读取,则会更新这个时间
    status time(stime): 文刚状态(eg: 权限)修改时,则会更新这个时间
    modification time(mtime): 文档内容变更时,则会更新这个时间 
    
    -a : 修改 access time 
    -c : 修改文档时间,若文档不存在,则新建
    -d : 后边可以接欲修订的日期而不用当前的日期
    -m : 仅修改 mtime
    -t : 后边可以接与修订的日期而不用当前的日期,格式: [YYYYMMDDhhmm]  


     
</ode></pre>

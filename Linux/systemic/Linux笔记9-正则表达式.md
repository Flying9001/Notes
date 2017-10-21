### Linux笔记-正则表达式
<pre><code>
1. 特殊字符
   * [:alnum:] : 表示英文大小写字母以及数字,即 0-9,A-Z,a-z
   * [:alpha:] : 表示所有英文大小写字母, 即 A-Z,a-z
    [:blank:] : 表示 空格键 以及 「tab」键
    [:cntrl:] : 表示键盘上边的控制键,包括 [Ctrl],[Tab],[Del],[Left]...等等
   * [:digit:] : 表示数字,即 0-9 
    [:graph:] : 表示除了 空格键 和 [Tab]键 之外的所有按键
   * [:lower:] : 表示小写字母,即 a-z
   * [:upper:] : 表示大写字母,即 A-Z
    [:print:] : 表示任何可以列出来的字符
    [:punct:] : 表示标点符号,即 : "" '' ? ! ; # $ 等等
    [:space:] : 表示任何产生空格的字符,包括 空格键,[Tab],CR 等等
    [:xdigit:] : 表示 16 位的数字,即 0-9,a-f,A-F
  其中标注 * 的比较常用

2. grep 进阶
    grep [option] 'search_str' filename : 在文件中搜寻关键字
        -A : 后边可以加数字(after),除了列出该行外,后续的 n 行也列出来
        -B : 后边可以加数字(before),除了列出该行外,前边的 n 行也列出来
        --color=auto : 用不同的颜色标记处关键字

3. 正则表达式汇总  
    ^ : 表示以开头,在[]内表示反向选择 eg: '^the' 表示以 the 开头 '[^the]' 表示
        不含 the
    $ : 表示以什么结束 eg: '[[:lower:]]$' 表示以 小写字母 结束
        grep  -v '^$' file | grep -v '^#' 打开 file ，去除空白行和以 # 开头的部分
    . : 表示一定有一个任意字符 eg: grep -n 'a.' file 查看 file 中一定有一个 a 部分
    \ : 转义字符,指跳过后边一个字符
    * : 表示任意长度任意字符  eg: grep -n 'a*' file 查看file所有(*:表示有0-无限个a)
    {n1,n2} 限定连续字符长度
        grep -n 'a\{2,\}' file : 查看 file 中 a 连续 >=2 次的行
        grep -n 'a\{2,5\}' file : 查看 file 中 a 连续 >=2 && <=5 次的行
    [list] : 指一定包含 [] 中的一个字符


    

   	











</code></pre>






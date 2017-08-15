## Linux 日志查询常用命令

<pre><code>
1. cat [option] file-name  查看文件(以文本形式) eg: cat test.txt  
  参数：  
    -n 查询前 n 行数据 eg: cat -n 10 text.txt  
    -b 查询前 n 行数据,去除空白行 eg: cat -b 10 text.log 
2. head [option] file-name 查看文件的前几行数据(默认 10 行) eg: head test.log
  参数:
    -n 查询前 n 行数据 eg: head -n 20 test.log 
3. tail [option] file-name 查询文件的后几行数据(默认 10 行) eg: tail test.log
  参数:
    -n 查询后 n 行数据 eg: tail -n 20 
    -f 查询后 10 行数据,并自动显示新增的文件内容(这个很重要,日志监控时可用) eg: tail -f test.log
    tail -n 50 -f test.log (前两个命令的组合) 显示 test.log 文件的后 50 行数据，并在文件内容增加后，自动显示新增的文件内容  

</code></pre>
## Linux 文件压缩工具

<pre><code>
1 gzip [option] file-name : gzip 解/压缩(gzip 可以打开compress/zip/gzip 等软件压缩的文档, gzip 压缩的
     文件后缀为 .gz,压缩后会将源文件删除)
    -c : 将压缩的资料输出到屏幕(实测一堆乱码)
    -d : 解压缩
    -t : 检验压缩文件的一致性(用于判断文件是否有误
    -v : 显示压缩比等信息
    -# : # 表示数字,设置压缩等级, 1 最快,压缩比最差, 9 最慢,压缩比最好, 默认为 6 
        eg: gzip -c test.txt  将 test.txt 文件压缩
2 bzip2 [option] file-name : bzip2 解/压缩(bzip2 压缩率高于 gzip, 支持保留源文件)
    -c : 将压缩的资料输出到屏幕(实测报错)
    -d : 解压缩
    -k : 保留源文件压缩
    -v : 显示压缩比等信息
    -# : 同 gzip (实测小文件看不出对比效果)
3 xz [option] file-name : xz 解/压缩
    -c : 将压缩的资料输出到屏幕
    -d : 解压缩
    -k : 保留源文件压缩
    -l : 列出压缩文件的而相关信息
    -v : 显示压缩比等信息
    -# : 同 gzip (实测小文件看不出对比效果)
4 tar [option] target-file source-file : 打包与解/压缩
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



     
</ode></pre>

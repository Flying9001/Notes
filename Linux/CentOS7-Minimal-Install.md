## CentOS 7 Minamal install (CentOS 7 最小化安装注意事项)

- 1 安装前使用[Tab]停止自动安装,在命令后添加 `inst.gpt` ,表示使用 GPT 分区
- 2 磁盘分区
<pre><code>  
    /biosboot  标准分区(standard)    2M
    /boot      标准分区(standard)    100M-1G
    /          LVM                  5G+
    /home      LVM                  2G+
    /swap      LVM                  512M-1G
      
</code></pre>     


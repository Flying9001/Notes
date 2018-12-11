## 在Linux下使用SSH方式管理VPS  

- **1.密码验证(每次登录需要输入密码)**  
在客户端打开终端(Terminal) --- 输入以下命令  
<pre><code>ssh root@ip
</code></pre>
然后输入密码进行登陆，登陆之后的操作时对远程VPS的操作(root:远程VPS用户名，也有可以为其他用户,下同；ip：远程VPS的ip地址,下同)  

- **2.公私密钥验证(第一次登陆后就不再需要输入密码)**  
在客户端生成私钥、公钥(注意:是在客户端)
<pre><code>ssh-keygen -t rsa
</code></pre>
输入以上命令之后会提示你输入生成密钥的路径以及密码，默认路径(/home/user_name/.ssh/id_rsa)  
公、私密钥生成后需要将公钥上传到远程VPS
<pre><code> ssh-copy-id -i ~/.ssh/id_rsa.pub root@ip
</code></pre>
最后,使用ssh登陆VPS 
<pre><code>ssh root@ip
</code></pre>
以后再使用本客户端通过ssh登陆将不需要输入密码









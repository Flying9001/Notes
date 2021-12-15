### Linux 用户和用户组管理  

创建用户  

```
useradd userName
```

给用户 `userName` 设置密码  

```
passwd userName xxx
```

创建工作组  

```
groupadd groupName
```

创建用户并将其加入到已经存在的工作组  

```
useradd -g groupName userName
```

将用户添加到工作组  

```
usermod -a groupName userName
```

查看本机所有用户和用户组  

```
cat /etc/passwd
```

参考文档:  

[Linux添加/删除用户和用户组](https://www.cnblogs.com/xd502djj/archive/2011/11/23/2260094.html "https://www.cnblogs.com/xd502djj/archive/2011/11/23/2260094.html")  

切换用户  

```
su userName
```

给用户添加目录权限  

```
sudo chown -R userGroup:userName /path
```


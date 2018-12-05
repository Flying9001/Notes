# Mysql 数据库笔记  

### 1. Mysql 数据库下载  

免安装: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/ "https://dev.mysql.com/downloads/mysql/")  

安装版: [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/ "https://dev.mysql.com/downloads/installer/")  



### 2. Mysql 安装与配置  

Windows: [MySQL5.7.19免安装版的安装与卸载重装](https://blog.csdn.net/Mrqiang9001/article/details/78070297 "https://blog.csdn.net/Mrqiang9001/article/details/78070297")  

Linux: [Linux/UNIX 上安装 MySQL](http://www.runoob.com/mysql/mysql-install.html "http://www.runoob.com/mysql/mysql-install.html")  

MacOS: [homebrew 安装mysql后，如何配置mysql](https://segmentfault.com/q/1010000000475470 "https://segmentfault.com/q/1010000000475470")  



### 3. Mysql 设置远程连接  

[Linux下远程连接MySQL数据库](https://www.jianshu.com/p/8fc90e518e2c "https://www.jianshu.com/p/8fc90e518e2c")  



### 4. Navicat 连接远程 Mysql  

[使用Navicat连接阿里云ECS服务器上的MySQL数据库](https://blog.csdn.net/nw_ningwang/article/details/76218997 "https://blog.csdn.net/nw_ningwang/article/details/76218997")  



### 5. 使用 Navicat 对 Mysql 数据库的导入与导出(包括sql与数据模型)  



### 6. [Mybatis+Mysql 返回插入的主键ID](http://gonethen.iteye.com/blog/2323804 "http://gonethen.iteye.com/blog/2323804")      



### 7 数据库测试的SQL脚本在执行之后必须注释，防止下次刷新脚本,全盘执行    



### 8 数据库增删字段规范    

要求:   

- 要有项目数据库文档,用以记录数据库的创建以及字段的增删;  
- 要在文档中说明在什么库、表中操作什么字段,并且标注字段的增删时间以及修改人;  
- 要写出操作数据库的 `sql` 语句  
- 添加的字段要有明确的注释(comment)  
- 尽量根据字段的重要性来确定字段的位置,而不是直接添加在最后    

```sql
-- 演示库 `demo` 用户表`t_user`新增语言类型字段`language`(2018-10-31,junqiang.lu)
ALTER TABLE `t_user` ADD `language` VARCHAR(10) DEFAULT 'zh' COMMENT '语言类型简写,en:英文,zh:中文' AFTER `nickName`;
```



### 9 常用数据库定义语言(Data Definition Language)    

#### 9.1 创建数据库    

- 数据库名为 `demodb`

```mysql
CREATE DATABASE `demodb`;
```

- 数据库名为 `demodb` ,指定数据库的字符集  

```mysql
CREATE DATABASE `demodb` DEFAULT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
```

#### 9.2 创建数据库表  

- 表名为 `user_info`,指定数据库引擎,字符集  

```mysql
drop table if exists user_info;
create table user_info
(
   id                   bigint unsigned not null auto_increment comment 'id 主键',
   user_name            varchar(30) not null comment '用户名',
   user_passcode        varchar(100) not null comment '登陆密码',
   user_email           varchar(50) comment '邮箱',
   user_insert_time     varchar(30) not null comment '用户注册时间',
   user_update_time     varchar(30) comment '用户更新时间',
   user_status          tinyint default 1 comment '用户账号状态,1正常(默认),2禁止登陆',
   user_version         int unsigned default 1 comment '版本控制字段(默认1)',
   user_del             tinyint default 0 comment '逻辑删除字段,0正常(默认),1删除',
   primary key (id)
)
ENGINE = INNODB DEFAULT
CHARSET = UTF8MB4;
alter table user_info comment '用户表';
```



#### 9.3  查看数据库、表信息    

- 查看所有数据库  

```mysql
SHOW DATABASES;
```

- 指定 使用/操作 某一个数据库,如 `demodb` 数据库  

```mysql
USE `demodb`;
```

- 查看当前数据库所有数据库表  

```mysql
SHOW TABLES;
```

- 查看数据库表的建表语句,如 `user_info` 表    

```mysql
SHOW CREATE TABLE `user_info`;
```



#### 9.4 数据库表的字段信息修改  

- 新增字段,添加字段  

```mysql
-- 用户表(user_info)添加用户性别字段(user_sex)
ALTER TABLE `user_info` ADD `user_sex` TINYINT NOT NULL DEFAULT '1' COMMENT '用户性别' AFTER `user_email`; 
```

- 修改字段数据类型  

```mysql
-- 修改用户表(user_info)邮箱字段(user_email)数据类型
ALTER TABLE `user_info` MODIFY `user_email` VARCHAR(100) COMMENT '用户邮箱';
```

- 修改字段名

```mysql
-- 将 user_info 表中字段名为 user_update_time 字段类型为 VARCHAR(30) 的字段改名为 user_modify_time
ALTER TABLE `user_info` CHANGE `user_update_time` `user_modify_time` VARCHAR(30); 
```

- 删除字段  

```mysql
-- 删除 user_info 表 user_id 字段
ALTER TABLE `user_info` DROP `user_id`;
```



#### 9.5 外键设置  

- 添加外键  

```mysql
-- user_info 表 user_id 字段关联 user 表 id(主键) 字段,外键名称为 fk_user_info_user_id
ALTER TABLE `user_info` ADD CONSTRAINT `fk_user_info_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT;
```

- 删除外键关联  

```mysql
-- 删除 user_info 表的 fk_user_info_user_id 外键
ALTER TABLE `user_info` DROP FOREIGN KEY `fk_user_info_user_id`;
```



### 10 数据库配置信息  

windows: `my.ini` 文件  

```ini
[client]
port=3306 ##端口
default-character-set=utf8mb4  ##字符编码
[mysqld]
port=3306  ##端口
character_set_server=utf8mb4 ##字符编码
basedir=%MYSQL_HOME%  ##根目录
datadir=%MYSQL_HOME%\data  ##数据库文件目录
[WinMySQLAdmin]
%MYSQL_HOME%\bin\mysqld.exe

```

说明: `MYSQL_HOME` 为系统环境变量  

Linux: `my.cnf` 文件  

[Mysql 之配置文件my.cnf](http://blog.51cto.com/zhujiangtao/1296931 "http://blog.51cto.com/zhujiangtao/1296931")  










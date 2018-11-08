# Mysql 数据库笔记  

### 1. Mysql 数据库下载

### 2. Windows 下 Mysql 免安装与配置

### 3. Linux 下 Mysql 安装与配置

### 4. Mysql 设置远程连接

### 5. Navicat 连接本地以及远程 Mysql

### 6. 使用 Navicat 对 Mysql 数据库的导入与导出(包括sql与数据模型)

### 7. Mysql 创建数据库、表并设置编码格式

### 8. [Mybatis+Mysql 返回插入的主键ID](http://gonethen.iteye.com/blog/2323804 "http://gonethen.iteye.com/blog/2323804")    

### 9 数据库测试的SQL脚本在执行之后必须注释，防止下次刷新脚本,全盘执行  

### 10 数据库增删字段规范    

要求:   

- 要有项目数据库文档,用以记录数据库的创建以及字段的增删;  
- 要在文档中说明在什么库、表中操作什么字段,并且标注字段的增删时间;  
- 要写出操作数据库的 `sql` 语句  
- 添加的字段要有明确的注释(comment)  
- 尽量根据字段的重要性来确定字段的位置,而不是直接添加在最后    

```sql
-- 演示库 `demo` 用户表`t_user`新增语言类型字段`language`(2018-10-31)
ALTER TABLE `t_user` ADD `language` VARCHAR(10) DEFAULT 'zh' COMMENT '语言类型简写,en:英文,zh:中文' AFTER `nickName`;
```










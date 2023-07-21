# 数据库设计规范  

- 1.时间放到最后  
- 2.字段统一使用小写字母,多个单词组合字段中间用下划线连接,如: `update_time`    
- 3.字段长度精确  
- 4.数据库设计文档化，使用专业的数据库设计工具(eg:powerDesigner)  
- 5.同一类型的字段,数据需要统一(eg:时间字段都用相同的字段类型格式)  
- 对于可以作为账号的字段(eg: 手机号、邮箱),需要做**唯一性**约束  

### 数据库表设计  

- 主键: bigint,非空,唯一性,自增长  
- 表编码格式(eg: utf-8)，数据库引擎(eg: Mysql 选择 InnoDB)

​    

### 数据库表必备字段  

```sql
  `insert_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `insert_operator` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加人用户 id',
  `insert_identity` tinyint(4) NOT NULL DEFAULT '1' COMMENT '添加人身份标识,0:前台用户;1:后台用户(默认)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_operator` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人用户 id',
  `update_identity` tinyint(4) NOT NULL DEFAULT '1' COMMENT '修改人身份标识;0:前台用户;1:后台用户(默认)',
  `versions` int(11) NOT NULL DEFAULT '1' COMMENT '版本号(默认1);用于更新时对比操作',
  `del_sign` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除;0:不删除(默认);1:逻辑删除;所有查询sql都要带上del=0这个条件',

```










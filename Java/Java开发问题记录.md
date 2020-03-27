# java 开发问题记录  

### 1. 关于使用 spring-data-redis 2.0+ 在Spring xml中配置(2018-04-28)  
  具体配置与调用可参考Spring-data 官方文档: [https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html](https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html "https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html")  
  Spring版本需要 **5.0+**
  低于此版本会抛出一下异常:  

```java
Caused by: java.lang.NoSuchMethodError: org.springframework.util.Assert.isTrue(ZLjava/util/function/Supplier;)V
at org.springframework.data.redis.connection.RedisStandaloneConfiguration.<init>(RedisStandaloneConfiguration.java:61)
at org.egov.infra.config.redis.RedisServerConfiguration.redisConnectionFactory(RedisServerConfiguration.java:86)
at org.egov.infra.config.redis.RedisServerConfigurationEnhancerBySpringCGLIBf89e20ba.CGLIBredisConnectionFactory1(<generated>)
at org.egov.infra.config.redis.RedisServerConfigurationEnhancerBySpringCGLIBf89e20baFastClassBySpringCGLIB1ac62136.invoke(<generated>)
at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:228)
at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:358)  
```

Stackoverflow相关问题: [NoSuchMethodError exception with spring-data-redis](https://stackoverflow.com/questions/46926643/nosuchmethoderror-exception-with-spring-data-redis "https://stackoverflow.com/questions/46926643/nosuchmethoderror-exception-with-spring-data-redis")  
    

### 2. Spring 读取多个properties配置文件  
  可参考: [Spring中配置和读取多个Properties文件](https://blog.csdn.net/anhuidelinger/article/details/70315863 "https://blog.csdn.net/anhuidelinger/article/details/70315863")  

### 3. java 正则表达式性能消耗问题  

[邮箱正则表达式优化，避免阻塞](https://blog.csdn.net/u012816142/article/details/48142137 "https://blog.csdn.net/u012816142/article/details/48142137")  

### 4. Spring Controller 方法为 `private` 时,AOP代理类无法获取参数  

[Spring的Controller方法声明为private获取注入属性为null到的问题 ](https://blog.csdn.net/weixin_41633157/article/details/80083636 "https://blog.csdn.net/weixin_41633157/article/details/80083636")  

### 5.Spring AOP 实现日志记录  

[Logging with Spring AOP](https://five.agency/logging-with-spring-aop/ "https://five.agency/logging-with-spring-aop/")  

[利用 AOP 打印方法入参、返回值、描述、耗时](https://segmentfault.com/a/1190000012030364)  

### 6. Mybatis 传入参数为List、Map等复杂参数写法  

### 7 java bean 在定义 boolean 类型字段时不要使用 isXxx  

### 8 Mabatis 中 resultMap 中定义的 `collection` 集合标签中的 `id` 属性必须定义  

否则会出现当 `result` 标签中 `column` 值相等时,本来有多个结果,但是只显示一个  

### 9 MySQL 数据库连接超时  

异常抛出:    

```
org.springframework.transaction.CannotCreateTransactionException: Could not open JDBC Connection for transaction; nested exception is com.mysql.cj.jdbc.exceptions.CommunicationsException: The last packet successfully received from the server was 54,124,574 milliseconds ago.  The last packet sent successfully to the server was 54,124,574 milliseconds ago. is longer than the server configured value of 'wait_timeout'. You should consider either expiring and/or testing connection validity before use in your application, increasing the server configured values for client timeouts, or using the Connector/J connection property 'autoReconnect=true' to avoid this problem.

```

解决办法:   

1) 如错误日志所提示,在 JDBC URL 后边添加 `autoReconnect=true`  

2) [ mysql超时：The last packet successfully received from the server was 172,848,658 milliseconds ago. ](https://blog.csdn.net/su20145104009/article/details/78579634)  

### 10 文件上传问题  

数据库保存文件相对路径,在取出时后台拼接出完整路径然后再返回给客户端  

### 11 SpringBoot 配置文件`application*.yml` 中属性值不能有下划线(`_`),否则无法识别  

### 12 SpringBoot 项目部署到外部 tomcat出现404  

参考: [Spring Boot打包war部署到tomcat，并解决404问题](https://my.oschina.net/yejunxi/blog/886023 "https://my.oschina.net/yejunxi/blog/886023")  

### 13 spring boot 抛出 NoSuchBeanDefinitionException 异常   

异常日志:  

```
2019-01-10 17:26:52:637 [restartedMain] WARN  o.s.b.w.s.c.AnnotationConfigServletWebServerApplicationContext(AbstractApplicationContext.java 556) -Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'commonController': Unsatisfied dependency expressed through field 'commonService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'commonService': Unsatisfied dependency expressed through field 'pdfExportConfig'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.ljq.demo.common.config.PDFExportConfig' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
2019-01-10 17:26:52:656 [restartedMain] INFO  com.alibaba.druid.pool.DruidDataSource(DruidDataSource.java 1825) -{dataSource-1} closed
2019-01-10 17:26:52:658 [restartedMain] INFO  org.apache.catalina.core.StandardService(DirectJDKLog.java 180) -Stopping service [Tomcat]
2019-01-10 17:26:52:673 [restartedMain] INFO  o.s.b.a.l.ConditionEvaluationReportLoggingListener(ConditionEvaluationReportLoggingListener.java 142) -

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2019-01-10 17:26:53:073 [restartedMain] ERROR o.s.b.diagnostics.LoggingFailureAnalysisReporter(LoggingFailureAnalysisReporter.java 42) -

***************************
APPLICATION FAILED TO START
***************************

Description:

Field pdfExportConfig in com.ljq.demo.springboot.service.impl.CommonServiceImpl required a bean of type 'com.ljq.demo.common.config.PDFExportConfig' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)


Action:

Consider defining a bean of type 'com.ljq.demo.common.config.PDFExportConfig' in your configuration.
```

出现场景: 引用其他模块的组件(Component),但是没有 spring boot 没有申明  

解决办法: 在 spring boot 的启动类上边添加`@ComponentScan(basePackages = {"com.xxx.xxx"})`注解  

`basePackages` 中必须包含所引用组件的包名(或父级包名)  

参考资料: [Spring NoSuchBeanDefinitionException](https://www.baeldung.com/spring-nosuchbeandefinitionexception "https://www.baeldung.com/spring-nosuchbeandefinitionexception")  

### 14 IntelliJ Idea解决Could not autowire. No beans of 'xxxx' type found的错误提示  

[IntelliJ Idea解决Could not autowire. No beans of 'xxxx' type found的错误提示](https://blog.csdn.net/u012453843/article/details/54906905 "https://blog.csdn.net/u012453843/article/details/54906905")  

### 15 Mysq 禁用 ONLY_FULL_GROUP_BY  

```sql
-- 查询mysql属性设置
-- SELECT @@sql_mode

-- 删除 mysql ONLY_FULL_GROUP_BY 属性
-- SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- SET SESSION sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

-- 添加 mysql ONLY_FULL_GROUP_BY 属性
-- SET sql_mode=(SELECT CONCAT(@@sql_mode,',ONLY_FULL_GROUP_BY'));
```

参考资料: [mysql 禁用 ONLY_FULL_GROUP_BY，暂时解决错误（sql_mode=only_full_group_by）](https://blog.csdn.net/weixin_34269583/article/details/88026517 "https://blog.csdn.net/weixin_34269583/article/details/88026517")  

### 16 MySQL 四种事务隔离级别  

[关于 MySQL 四种事务隔离级别的设置与简要说明](https://blog.csdn.net/Mrqiang9001/article/details/105143411 "https://blog.csdn.net/Mrqiang9001/article/details/105143411")  












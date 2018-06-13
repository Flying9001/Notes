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








# java 开发问题记录  

### 1. 关于使用 spring-data-redis 2.0+ 在Spring xml中配置(2018-04-28)  
  具体配置与调用可参考Spring-data 官方文档: [https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html](https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html "https://docs.spring.io/spring-data/redis/docs/2.0.0.RELEASE/reference/html")  
  Spring版本需要 **5.0+**
  低于此版本会抛出一下异常:  
<pre><code>
Caused by: java.lang.NoSuchMethodError: org.springframework.util.Assert.isTrue(ZLjava/util/function/Supplier;)V
at org.springframework.data.redis.connection.RedisStandaloneConfiguration.<init>(RedisStandaloneConfiguration.java:61)
at org.egov.infra.config.redis.RedisServerConfiguration.redisConnectionFactory(RedisServerConfiguration.java:86)
at org.egov.infra.config.redis.RedisServerConfiguration$$EnhancerBySpringCGLIB$$f89e20ba.CGLIB$redisConnectionFactory$1(<generated>)
at org.egov.infra.config.redis.RedisServerConfiguration$$EnhancerBySpringCGLIB$$f89e20ba$$FastClassBySpringCGLIB$$1ac62136.invoke(<generated>)
at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:228)
at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:358)
</code></pre>
    
  Stackoverflow相关问题: [NoSuchMethodError exception with spring-data-redis](https://stackoverflow.com/questions/46926643/nosuchmethoderror-exception-with-spring-data-redis "https://stackoverflow.com/questions/46926643/nosuchmethoderror-exception-with-spring-data-redis")  
    
### 2. Spring 读取多个properties配置文件  
  可参考: [Spring中配置和读取多个Properties文件](https://blog.csdn.net/anhuidelinger/article/details/70315863 "https://blog.csdn.net/anhuidelinger/article/details/70315863")  
    


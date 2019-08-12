# spring-cloud-docker
spring-cloud-docker learning sample .

jar包启动顺序：
1. 启动eureka server。  
java -jar micro-discovery-eureka-backup-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1  
java -jar micro-discovery-eureka-backup-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2  

2. 启动用户服务提供微服务。  
java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  

3. 启动电影消费微服务。  
java -jar micro-consumer-movie-0.0.1-SNAPSHOT.jar  

4. eureka server访问  
http://peer1:8761/  
http://peer2:8762/  

5. 查询接口:  
http://peer1:8010/user/2  

6. 启动eureka server用户认证微服务。  
java -jar micro-discovery-eureka-authenticating-0.0.1-SNAPSHOT.jar  

7. 微服务元数据jar包启动顺序。  
   a) 启动eureka server。  
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   b) 启动用户提供微服务(拥有eureka元数据)。  
   java -jar micro-provider-user-my-metadata-0.0.1-SNAPSHOT.jar  
   c）启动电影消费微服务（查询eureka元数据)。  
   java -jar micro-consumer-movie-understanding-metadata-0.0.1-SNAPSHOT.jar  
   d) 查询接口(eureka自定义元数据)。  
   http://localhost:8761/eureka/apps  
   http://localhost:8010/user-instance  

8. Eureka Server的REST端点  
   XML方式注册命令：  
   cat ./eureka-rest-api-test.xml | curl -v -X POST -H "Content-type: application/xml" -d @- http://localhost:8761/eureka/apps/rest-api-test  
   访问：  
   http://localhost:8761/eureka/apps/rest-api-test  
   注销微服务实例：
   curl -v -X DELETE http://localhost:8761/eureka/apps/rest-api-test/lc:rest-api-test:9000  

9. Ribbon结合Eureka  
   启动jar包顺序：
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT-0.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   java -jar micro-consumer-movie-ribbon-0.0.1-SNAPSHOT.jar  
   ribbon请求中，请使用用户微服务的虚拟主机名  
   访问链接：
   http://localhost:8010/user/2  
   http://localhost:8010/log-instance  
   
10. 自定义Ribbon规则：  
   启动jar包顺序：
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT-0.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   java -jar micro-consumer-movie-ribbon-custom-0.0.1-SNAPSHOT.jar  
   多次访问：
   http://localhost:8010/log-instance  

11. 使用applicaion.yml属性配置Ribbon规则。  
   java -jar micro-consumer-movie-ribbon-custom-properties-0.0.1-SNAPSHOT.jar  


12. 脱离Eureka使用Ribbon负载均衡。  
   启动jar包顺序： 
   java -jar micro-simple-provider-user-0.0.1-SNAPSHOT.jar  
   java -jar micro-simple-provider-user-0.0.1-SNAPSHOT.jar --server.port=8001  
   java -jar micro-consumer-movie-without-eureka-0.0.1-SNAPSHOT.jar  
   多次访问：  
   http://localhost:8010/log-instance  
   说明： Ribbon使用中：@LoadBalanced注解必须有。  

13. 整合Feign
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT-0.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   java -jar micro-consumer-movie-feign-0.0.1-SNAPSHOT.jar  
   访问：
   http://localhost:8010/user/2  
   注解：  
   @FeignClient(name = "micro-provider-user")  
   @EnableFeignClients  

14. 自定义Feign  
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT-0.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT.jar   
   java -jar micro-consumer-movie-feign-custom-0.0.1-SNAPSHOT.jar  
   访问：
   http://localhost:8010/user/2  
   注解：  
   @Configuration  
   @FeignClient(name = "micro-provider-user", configuration = FeignConfig.class)  
   @RequestLine("GET /{id}")  

15. 用户提供微服务（带认证）  
   pom依赖：spring-boot-starter-security
   java -jar micro-provider-user-with-auth-0.0.1-SNAPSHOT.jar   
   注解：
   @Configuration  
   @EnableWebSecurity  
   @EnableGlobalMethodSecurity(prePostEnabled = true)  
   访问：  
   http://localhost:8000/2  

16. 手动创建Feign  
   @Import(FeignClientsConfiguration.class)  
   this.userFeignClient初期赋值    
   启动jar：  
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-with-auth-0.0.1-SNAPSHOT.jar  
   java -jar micro-consumer-movie-feign-manual-0.0.1-SNAPSHOT.jar  
   访问：
   http://localhost:8010/user-admin/2  
   http://localhost:8010/user-user/2  

17. Feign日志处理  
   日志配置类:com.lc.cloud.config.FeignLogConfig  
   Logger.Level.FULL;   
   Feign的日志只对DEBUG作出响应  
   启动jar：  
   java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   java -jar micro-consumer-movie-feign-logging-0.0.1-SNAPSHOT.jar  
   
18. ribbon整合Hystrix  
   启动类：@EnableCircuitBreaker, @EnableHystrix  
   controller类：@HystrixCommand(fallbackMethod = "findByIdFallback")  
   启动jar：  
   1) java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   2) java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   3) java -jar micro-consumer-movie-ribbon-hystrix-0.0.1-SNAPSHOT.jar  
   访问：  
   http://localhost:8010/user/2  
   停止2），再次访问http://localhost:8010/user/2  

19. feign整合Hystrix  
   spring cloud默认已为Feign整合了Hystrix  
   Feign接口上添加  
   @FeignClient(name = "micro-provider-user", fallback = FeignClientFallback.class)
   属性文件中：  
   feign.hystrix.enabled=true
   启动jar：  
   1) java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   2) java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   3) java -jar micro-consumer-movie-feign-hystrix-fallback-0.0.1-SNAPSHOT.jar  
   访问：  
   http://localhost:8010/user/2  
   停止2），再次访问http://localhost:8010/user/2 

20. feign整合Hystrix查看回退原因  
   @FeignClient(name = "micro-provider-user", fallbackFactory = FeignClientFallbackFactory.class)  
   class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {  
   log.info("fallback; reason was " + cause);  
   属性文件中：  
   feign.hystrix.enabled=true  
   启动jar：  
   1) java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar  
   2) java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
   3) java -jar micro-consumer-movie-feign-hystrix-fallback-factory-0.0.1-SNAPSHOT.jar  
   访问：  
   http://localhost:8010/user/2  
   停止2），再次访问http://localhost:8010/user/2  
   显示回退原因：  
   fallback; reason was java.lang.RuntimeException: com.netflix.client.ClientException: Load balancer does not have available server for client: micro-provider-user  
  
21. Hystrix的监控  
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-consumer-movie-ribbon-hystrix-0.0.1-SNAPSHOT.jar  
    访问：  
    http://localhost:8010/user/2  
    http://localhost:8010/hystrix.stream  
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-consumer-movie-feign-hystrix-fallback-stream-0.0.1-SNAPSHOT.jar  
    访问：  
    http://localhost:8010/user/2  
    http://localhost:8010/hystrix.stream   

22. Hystrix Dashboard监控  
    注解：@EnableHystrixDashboard  
    启动jar： java -jar micro-hystrix-dashboard-0.0.1-SNAPSHOT.jar  
    访问：  
    http://localhost:8030/hystrix  
    输入：  
    http://localhost:8010/hystrix.stream

23. Hystrix Turbine监控  
    注解：@EnableTurbine  
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-consumer-movie-ribbon-hystrix-0.0.1-SNAPSHOT.jar  
    4）java -jar micro-consumer-movie-feign-hystrix-fallback-stream-0.0.1-SNAPSHOT.jar
    5）java -jar micro-hystrix-turbine-ha-0.0.1-SNAPSHOT.jar  
    6）java -jar micro-hystrix-dashboard-0.0.1-SNAPSHOT.jar  
    访问：
    http://localhost:8010/user/2  
    http://localhost:8020/user/2  
    http://localhost:8030/hystrix.stream  
    http://localhost:8031/turbine.stream  
    Monitor stream click.  

24. Rabbit 使用消息中间件收集数据。  
    注解：@EnableTurbineStream  
    配置application.yml  
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-consumer-movie-ribbon-hystrix-turbine-mq-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-hystrix-turbine-rabbitmq-0.0.1-SNAPSHOT.jar  
    访问：  
    http://localhost:8010/user/2  获取正常结果。  
    http://localhost:8031/  
    Turbine能够持续不断地显示监控数据。  
    
25. Zuul路由规则。  
    注解：@EnableZuulProxy  
    声明一个代理，该代理使用Ribbon来定位注册在Eureka Server中的微服务；同时还整合了Hystrix.  
    默认情况下，Zuul会代理所有注册到Eureka Server的微服务。  
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-provider-user-0.0.1-SNAPSHOT.jar  
    3）java -jar micro-consumer-movie-ribbon-0.0.1-SNAPSHOT.jar  
    4）java -jar micro-gateway-zuul-0.0.1-SNAPSHOT.jar  
    访问：  
    http://localhost:8040/micro-consumer-movie-ribbon/user/3  
    http://localhost:8040/micro-provider-user/2  
    Zuul路由规则： 
    http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka上的serviceId/**会被转发到serviceId对应的微服务。  
    Zuul可以使用Ribbon达到负载均衡的效果。  
    测试Hystrix容错与监控：  
    5）java -jar micro-hystrix-dashboard-0.0.1-SNAPSHOT.jar   
    访问：  
    http://localhost:8030/hystrix  
    输入：  
    http://localhost:8040/hystrix.stream

26. Zuul的路由端点：  
    路由管理端点/routes: 方便、直观地查看以及管理Zuul的路由。  
    注意配置文件中加入：  
    management.security.enabled: false
    http://localhost:8040/routes  

27. 文件上传。
    启动jar：  
    1）java -jar micro-discovery-eureka-0.0.1-SNAPSHOT.jar   
    2）java -jar micro-file-upload-0.0.1-SNAPSHOT.jar   
    3）java -jar micro-gateway-zuul-0.0.1-SNAPSHOT.jar  
    访问：  
    curl -F "file=@smallsize.zip" localhost:8050/upload  
    curl -F "file=@largesize.zip" localhost:8050/upload  
    通过Zuul上传小文件    
    curl -v -H "Transfer-Encoding: chunked" -F "file=@smallsize.zip" localhost:8040/micro-file-upload/upload
    
    通过Zuul上传大文件，不添加前缀/zuul：  
    curl -v -H "Transfer-Encoding: chunked" -F "file=@largesize.zip" localhost:8040/micro-file-upload/upload  
    会报错：  
    FileUploadBase$FileSizeLimitExceededException: The field file exceeds its maximum permitted size of 1048576 bytes  
    通过Zuul上传大文件，添加前缀/zuul：  
    curl -v -H "Transfer-Encoding: chunked" -F "file=@largesize.zip" localhost:8040/zuul/micro-file-upload/upload  
    会报错：  
    com.netflix.hystrix.exception.HystrixRuntimeException: micro-file-upload timed-out and no fallback available.  
    3）java -jar micro-gateway-zuul-file-upload-0.0.1-SNAPSHOT.jar  
    curl -v -H "Transfer-Encoding: chunked" -F "file=@rightsize.avi" localhost:8040/zuul/micro-file-upload/upload
    
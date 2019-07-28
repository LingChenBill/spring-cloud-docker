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
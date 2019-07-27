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
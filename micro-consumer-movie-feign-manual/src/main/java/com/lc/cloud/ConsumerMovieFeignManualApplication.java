package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 电影微服务（服务消费者）
 * { @EnableDiscoveryClient: 将电影消息微服务注册到Eureka server。
 *   去掉@EnableFeignClients: 开启Feign调用。
 *   手动配置Feign }
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
public class ConsumerMovieFeignManualApplication {

    public static void main( String[] args ) {
        SpringApplication.run(ConsumerMovieFeignManualApplication.class, args);
    }
}

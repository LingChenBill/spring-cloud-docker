package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 电影微服务（服务消费者）
 * { @EnableDiscoveryClient: 将电影消息微服务注册到Eureka server。
 *   @EnableFeignClients: 开启Feign调用。}
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMovieFeignApplication {

    public static void main( String[] args ) {
        SpringApplication.run(ConsumerMovieFeignApplication.class, args);
    }
}

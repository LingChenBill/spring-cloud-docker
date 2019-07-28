package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 电影微服务（服务消费者）
 * { 脱离Eureka使用Ribbon: @LoadBalanced注解必须要。 }
 *
 * @author zyz.
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class ConsumerMovieWithoutEurekaApplication {

    /**
     * 实例化restTemplate Bean,并使用该方法的名称命名。
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main( String[] args ) {
        SpringApplication.run(ConsumerMovieWithoutEurekaApplication.class, args);
    }
}

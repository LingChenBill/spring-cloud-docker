package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka server 微服务入口。
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main( String[] args ) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}

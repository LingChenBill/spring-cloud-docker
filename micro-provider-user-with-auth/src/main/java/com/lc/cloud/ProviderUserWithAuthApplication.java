package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * User 服务提供类入口
 * { @EnableDiscoveryClient: 声明这个一个Eureka client。 }
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserWithAuthApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ProviderUserWithAuthApplication.class, args);
    }
}

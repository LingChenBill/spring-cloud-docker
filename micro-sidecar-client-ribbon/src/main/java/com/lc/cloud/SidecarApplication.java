package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * sidecar相关微服务入口。
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SidecarApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SidecarApplication.class, args);
    }

    /**
     * 集成负载均衡。
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

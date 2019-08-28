package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * sidecar 微服务入口。
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableSidecar
public class SidecarApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SidecarApplication.class, args);
    }
}

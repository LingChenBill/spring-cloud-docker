package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * File upload Entry class.
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableEurekaClient
public class FileuploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }
}

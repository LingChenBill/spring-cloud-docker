package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Hystrix Turbine rabbit application.
 */
@SpringBootApplication
@EnableTurbineStream
public class HystrixTurbineRabbitApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineRabbitApp.class, args);
    }
}

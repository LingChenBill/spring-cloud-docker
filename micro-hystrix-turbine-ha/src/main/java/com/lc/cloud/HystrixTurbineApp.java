package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hystrix Turbine application.
 */
@SpringBootApplication
@EnableTurbine
public class HystrixTurbineApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApp.class, args);
    }
}

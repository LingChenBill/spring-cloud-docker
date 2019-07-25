package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 电影微服务（服务消费者）
 *
 * @author zyz.
 */
@SpringBootApplication
public class ConsumerMovieApplication {

    /**
     * 实例化restTemplate Bean,并使用该方法的名称命名。
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main( String[] args ) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }
}

package com.lc.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Feign日志配置.
 *
 * @author zyz.
 */
@Configuration
public class FeignLogConfig {

    /**
     * Feign日志级别:NONE, BASIC, HEADERS, FULL
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}

package com.lc.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon配置--Ribbon规则。
 *
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule(){
        // 随机的负载均衡规则。
        return new RandomRule();
    }
}

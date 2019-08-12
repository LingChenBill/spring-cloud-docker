package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul 网关启动类。
 * { @EnableZuulProxy: 声明一个Zuul代理。
 *   通过zuul上传大文件，超时配置。}
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulFileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulFileUploadApplication.class, args);
    }
}

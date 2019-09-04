package com.lc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config Server 启动类。
 * { @EnableConfigServer: 声明这是一个Config Server.
 *   端点与配置文件的映射规则:
 *   /{application}/{profile}[/{label}]
 *   /{application}-{profile}.yml
 *   /{label}/{application}-{profile}.yml
 * }
 *
 * @author zyz.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

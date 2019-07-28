package com.lc.cloud.configHelper;

import com.lc.cloud.config.RibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用RibbonClient,为特定的name的Ribbon Client自定义配置。
 * { @RibbonClient：指定Ribbon的配置类。
 *   此类可删除。}
 *
 * @author zyz.
 */
@Configuration
@RibbonClient(name = "micro-provider-user", configuration = RibbonConfig.class)
public class RibbonConfigHelper {
}

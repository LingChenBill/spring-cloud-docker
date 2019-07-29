package com.lc.cloud.feign;

import com.lc.cloud.config.FeignConfig;
import com.lc.cloud.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign接口。
 * { @FeignClient的configuration属性，指定feign的配置类。 }
 *
 * @author zyz.
 */
@FeignClient(name = "micro-provider-user", configuration = FeignConfig.class)
public interface UserFeignClient {

    /**
     * 查询用户接口。
     *
     * @param id
     * @return
     */
//    @GetMapping(value = "/{id}")
//    public User findById(@PathVariable("id") Long id);
    @RequestLine("GET /{id}")
    public User findById(@Param("id") Long id);
}

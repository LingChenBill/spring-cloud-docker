package com.lc.cloud.feign;

import com.lc.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign接口。
 *
 * @author zyz.
 */
@FeignClient(name = "micro-provider-user")
public interface UserFeignClient {

    /**
     * 查询用户接口。
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") Long id);
}

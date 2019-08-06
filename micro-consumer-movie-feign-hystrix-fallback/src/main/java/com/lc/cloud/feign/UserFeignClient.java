package com.lc.cloud.feign;

import com.lc.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign接口。
 *
 * @author zyz.
 */
@FeignClient(name = "micro-provider-user", fallback = FeignClientFallback.class)
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

@Component
class FeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-1l);
        user.setName("默认用户");
        return user;
    }

}

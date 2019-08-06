package com.lc.cloud.feign;

import com.lc.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign接口。
 *
 * @author zyz.
 */
@FeignClient(name = "micro-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
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

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法。
 *
 */
@Component
@Slf4j
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                log.info("fallback; reason was " + cause);
                User user = new User();
                user.setId(-1l);
                user.setName("默认用户");
                return user;
            }
        };
    }
}

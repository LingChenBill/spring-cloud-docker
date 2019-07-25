package com.lc.cloud.controller;

import com.lc.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 电影服务controller.
 * { 电影消费者通过restTemplate方式来调用用户提供微服务接口。}
 *
 * @author zyz.
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    /**
     * 使用RestTemplate请求用户微服务的API。
     * { 接口地址：url硬编码在代码中。 }
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
//        return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
        return this.restTemplate.getForObject(this.userServiceUrl + id, User.class);

    }
}

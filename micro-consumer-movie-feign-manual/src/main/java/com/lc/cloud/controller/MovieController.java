package com.lc.cloud.controller;

import com.lc.cloud.entity.User;
import com.lc.cloud.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电影服务controller.
 * { 电影消费者通过restTemplate方式来调用用户提供微服务接口。
 *   @Import(FeignClientsConfiguration.class): Spring Cloud为Feign默认提供的配置类。}
 *
 * @author zyz.
 */
@RestController
@Import(FeignClientsConfiguration.class)
public class MovieController {

    private UserFeignClient userFeignClient;

    private UserFeignClient adminUserFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userFeignClient = Feign.builder().client(client).encoder(encoder)
                .decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "p1"))
                .target(UserFeignClient.class, "http://micro-provider-user-with-auth/");
        this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder)
                .decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "p2"))
                .target(UserFeignClient.class, "http://micro-provider-user-with-auth/");
    }

    /**
     * 调用Feign接口。
     *
     * @param id
     * @return
     */
    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }

    /**
     * 调用Feign接口。
     *
     * @param id
     * @return
     */
    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return this.adminUserFeignClient.findById(id);
    }

}

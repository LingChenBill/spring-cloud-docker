package com.lc.cloud.controller;

import com.lc.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
@Slf4j
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 使用RestTemplate请求用户微服务的API。
     * { 接口地址：url硬编码在代码中。 }
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        // ribbon负载均衡(当Ribbon与Eureka配合使用时，会自动将虚拟主机名映射成微服务的网络地址。)。
        return this.restTemplate.getForObject("http://micro-provider-user/" + id, User.class);
    }

    /**
     * 获取节点信息。
     *
     */
    @GetMapping("/log-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("micro-provider-user");

        log.info("service Id: " + serviceInstance.getServiceId() + ", Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort());
    }

    /**
     * 容断器--容错处理。
     *
     * @param id
     * @return
     */
    public User findByIdFallback(Long id) {
        User user = new User();
        user.setId(-1l);
        user.setName("默认用户");
        return user;
    }


}

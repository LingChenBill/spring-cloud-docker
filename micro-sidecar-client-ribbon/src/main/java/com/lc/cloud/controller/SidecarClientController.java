package com.lc.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Sidecar client controller.
 *
 * @author zyz.
 */
@RestController
public class SidecarClientController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 访问Sidecar 微服务的接口。
     * { JVM微服务调用非JVM微服务的接口。}
     *
     * @return
     */
    @GetMapping("/test")
    public String findById() {
        return this.restTemplate.getForObject("http://micro-sidecar/", String.class);
    }
}

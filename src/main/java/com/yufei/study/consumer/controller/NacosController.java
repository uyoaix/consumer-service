package com.yufei.study.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yufei.wang
 * @date 2021/11/16 19:59
 */
@RestController
public class NacosController {

    @Value("${spring.application.name}")
    private String appName;

    private final LoadBalancerClient loadBalancerClient;

    private final RestTemplate restTemplate;

    public NacosController(LoadBalancerClient loadBalancerClient,
                           RestTemplate restTemplate) {
        this.loadBalancerClient = loadBalancerClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/echo/app-name")
    public String echoAppName() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-server");
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        System.out.println("request url:" + url);
        return restTemplate.getForObject(url, String.class);
    }
}

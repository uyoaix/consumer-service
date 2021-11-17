package com.yufei.study.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConsumerServiceApplication.class, args);
        String name = applicationContext.getEnvironment().getProperty("name");
        System.out.println(" name: " + name);

        String key = applicationContext.getEnvironment().getProperty("test.key");
        String value = applicationContext.getEnvironment().getProperty("test.value");
        System.out.println("key: " + key + ", value: " + value);

        String dateId = applicationContext.getEnvironment().getProperty("data.id");
        System.out.println("dataId: " + dateId);
    }

}

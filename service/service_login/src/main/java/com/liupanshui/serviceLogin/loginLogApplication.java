package com.liupanshui.serviceLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.liupanshui"})
@EnableDiscoveryClient  //nacos 注册
@EnableFeignClients //启动 feign 客户端


public class loginLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(loginLogApplication.class,args);
    }
}

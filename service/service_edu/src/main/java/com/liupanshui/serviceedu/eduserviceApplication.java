package com.liupanshui.serviceedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"com.liupanshui"})
@MapperScan("com.liupanshui.serviceedu.mapper")
@CrossOrigin
public class eduserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(eduserviceApplication.class,args);
    }
}

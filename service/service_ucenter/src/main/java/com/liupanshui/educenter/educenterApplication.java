package com.liupanshui.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication// 取消数据源自动配置
@ComponentScan(basePackages = {"com.liupanshui"})
@MapperScan("com.liupanshui.educenter.mapper")
@CrossOrigin
public class educenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(educenterApplication.class,args);
    }
}

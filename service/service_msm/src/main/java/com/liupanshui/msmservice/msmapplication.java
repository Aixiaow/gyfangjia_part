package com.liupanshui.msmservice;

import jdk.nashorn.internal.objects.annotations.ScriptClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 短信模块
 */
@ComponentScan({"com.liupanshui"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@CrossOrigin
public class msmapplication {
    public static void main(String[] args) {
        SpringApplication.run(msmapplication.class,args);
    }
}

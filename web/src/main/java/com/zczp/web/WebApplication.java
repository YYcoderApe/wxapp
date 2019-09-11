package com.zczp.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.zczp.controller_cancer","com.zczp.controller_yycoder"
,"com.zczp.service_cancer","com.zczp.service_yycoder","com.zczp.util","com.zczp.dao","com.zczp.config"
,"com.zczp.web"})
@MapperScan("com.zczp.dao")
@EnableCaching
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }


}

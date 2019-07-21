package com.example.wxappdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wxappdemo.mapper")
public class WxappdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxappdemoApplication.class, args);
    }

}

package com.zczp.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.zczp.cmsController","com.zczp.cmsController_yycoder","com.zczp.service_yycoder"
        ,"com.zczp.cmsService","com.zczp.service_cancer","com.zczp.util","com.zczp.dao","com.zczp.config"
        ,"com.zczp.cms"})
@MapperScan("com.zczp.dao")
@SpringBootApplication
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}

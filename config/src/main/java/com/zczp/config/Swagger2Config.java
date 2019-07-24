package com.zczp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //标记配置类
@EnableSwagger2 //开启在线接口文档
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.zczp"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档标题
                .title("api文档")
                //文档描述
                .description("简单优雅的restfun风格")
                //文档联系方式
//          .contact(new Contact("作者", "网站", "邮箱"))
//          .termsOfServiceUrl("http://blog.csdn.net/forezp")
//          .license("xxx")
//          .licenseUrl("http://www.xxx.com")
                // 版本信息
                .version("1.0")
                .build();
    }
}

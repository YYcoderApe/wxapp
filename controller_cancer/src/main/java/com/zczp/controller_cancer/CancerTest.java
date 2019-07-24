package com.zczp.controller_cancer;


import com.zczp.service_cancer.ServiceTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cancer")
@Api(tags = "测试类")
public class CancerTest {
    @RequestMapping("/showAll")
    @ApiOperation("展示信息")
    public  String showAll(){
        ServiceTest serviceTest =new ServiceTest();
        return serviceTest+" 我是web ";
    }
}

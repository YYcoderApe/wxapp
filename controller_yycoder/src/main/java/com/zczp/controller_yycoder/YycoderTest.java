package com.zczp.controller_yycoder;

import com.zczp.service_yycoder.ServiceTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("yycoder")
@Api(tags = "yy测试类")
public class YycoderTest {
    @RequestMapping("showAll")
    @ApiOperation("展示信息")
    public  String showAll(){
        ServiceTest serviceTest =new ServiceTest();
        return serviceTest+" 我是web ";
    }
}

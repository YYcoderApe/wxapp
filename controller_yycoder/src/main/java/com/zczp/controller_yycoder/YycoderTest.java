package com.zczp.controller_yycoder;

import com.zczp.service_yycoder.ServiceTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("yycoder")
public class YycoderTest {
    @RequestMapping("showAll")
    public  String showAll(){
        ServiceTest serviceTest =new ServiceTest();
        return serviceTest+" 我是web ";
    }
}

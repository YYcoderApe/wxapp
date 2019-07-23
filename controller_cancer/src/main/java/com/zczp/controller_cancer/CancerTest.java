package com.zczp.controller_cancer;


import com.zczp.service_cancer.ServiceTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("cancer")
public class CancerTest {
    @RequestMapping("showAll")
    public  String showAll(){
        ServiceTest serviceTest =new ServiceTest();
        return serviceTest+" 我是web ";
    }
}

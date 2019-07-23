package com.example.wxappdemo.controller;

import com.example.wxappdemo.entity.TbUser;
import com.example.wxappdemo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("/login")
    public String login(){
        TbUser tbUser=loginService.selectbyid(1);
        System.out.println(tbUser);
        return "tbUser";
    }
}

package com.zczp.controller_cancer;

import com.zczp.service_cancer.Impl.WxUserServiceImpl;
import com.zczp.vo_cancer.AuthorizeVO;
import com.zczp.vo_cancer.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户登录模块")
@RequestMapping("/api/wx")
public class WxUserController {

    @Autowired
    private WxUserServiceImpl wxUserService;

    @ApiOperation("授权登录")
    @PostMapping("/authorize")
    public String authorize(@RequestBody  AuthorizeVO authorizeVO){
        return wxUserService.authorize(authorizeVO);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public String login(@RequestParam String code){
        return wxUserService.login(code);
    }
}

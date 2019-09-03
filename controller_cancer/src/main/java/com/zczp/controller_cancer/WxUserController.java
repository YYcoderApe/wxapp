package com.zczp.controller_cancer;

import com.zczp.entity.TbUser;
import com.zczp.service_cancer.Impl.WxUserServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.JwtUtil;
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
@Api(tags = "用户保存个人信息")
@RequestMapping("/api/wx")
public class WxUserController {

    @Autowired
    private WxUserServiceImpl wxUserService;
    @Autowired
    private AjaxResult ajaxResult;
//    @ApiOperation("保存个人信息")
//    @PostMapping("/save")
//    public AjaxResult save(@RequestBody TbUser tbUser){
//        int result= wxUserService.save(tbUser);
//        if (result==1){
//            return ajaxResult.ok("成功");
//        }
//        return ajaxResult.error("失败");
//    }
    @ApiOperation("授权登录")
    @PostMapping("/authorize")
    public String authorize(@RequestBody  AuthorizeVO authorizeVO){
        return wxUserService.authorize(authorizeVO);
    }

//    @ApiOperation("获取")
//    @PostMapping("/openid")
//    public String getOpenid(@RequestParam String token){
//        return "OPENID:"+new JwtUtil().getWxOpenIdByToken(token)+"SESSION:"+new JwtUtil().getSessionKeyByToken(token);
//    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public String login(@RequestParam String code){
        return wxUserService.login(code);
    }
}

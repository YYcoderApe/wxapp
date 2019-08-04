package com.zczp.controller_yycoder;


import com.zczp.service_yycoder.AdminService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
@Api(tags = "管理员处理模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    @ApiOperation("管理员进行登录")
    public AjaxResult adminLogin(
            @RequestParam @ApiParam("Admin名字") String adminName,
            @RequestParam @ApiParam("Admin密码") String adminPwd){
        boolean result = adminService.checkAdmin(adminName,adminPwd);
        if(result){
            return new AjaxResult().error("登录成功");
        }
        return new AjaxResult().error("用户名或密码错误，请重新输入");

    }
}

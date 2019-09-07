package com.zczp.cmsController_yycoder;


import com.zczp.service_cancer.TbCommentService;
import com.zczp.service_cancer.TbPostService;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员 进行 对用户信息的管理
 */
@RestController
@RequestMapping("userInfo")
@Api(tags = "用户管理模块")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    @ApiOperation("用户信息  --- 获取所有用户")
    public AjaxResult getAllUser(){
        List<UserDetailVo> userDetailVoList = userService.getAllUser(0);
        if (userDetailVoList != null) {
            return new AjaxResult().ok(userDetailVoList);
        }
        return new AjaxResult().error("数据库用户表无信息");
    }

    @GetMapping("/searchUserInfo")
    @ApiOperation("用户信息 --- 根据昵称搜索")
    public AjaxResult getAllUsert(@RequestParam @ApiParam("用户昵称") String userName) {
        List<UserDetailVo> userDetailVoList= userService.searchUserByName(userName);
        if(userDetailVoList!=null){
            return new AjaxResult().ok(userDetailVoList);
        }
        return new AjaxResult().error("该用户不存在");
    }

    @GetMapping("UserCollectLaist")
    @ApiOperation("收藏情况 --- 查看用户的收藏")
    public AjaxResult getUserCollection(
            @RequestParam @ApiParam("当前用户的openId") String openId) {
        List<CollectPostDetailVo> postDetailVoList = userService.getUserCollection(openId);
        if (postDetailVoList != null) {
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有收藏任何东西");
    }

}

package com.zczp.cmsController_yycoder;


import com.zczp.service_cancer.TbPostService;
import com.zczp.service_yycoder.AdminService;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.util.JwtUtil;
import com.zczp.util.PageQueryUtil;
import com.zczp.util.PageResult;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.PostDetailVo;
import com.zczp.vo_yycoder.UserDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员 进行 对用户信息的管理
 */
@RestController
@RequestMapping("admin")
@Api(tags = "后台管理系统模块")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private TbPostService tbPostService;

    @Autowired
    private AskReplyService askReplyService;

    PageQueryUtil pageUtil;
    PageResult pageResult;

    @GetMapping("/login")
    @ApiOperation("管理员登录")
    public AjaxResult adminLogin(
            @RequestParam @ApiParam("Admin名字") String adminName,
            @RequestParam @ApiParam("Admin密码") String adminPwd) {
        boolean result = adminService.checkAdmin(adminName, adminPwd);
        if (result) {
//            //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
//            String JWT = JwtUtil
            return new AjaxResult().error("登录成功");
        }
        return new AjaxResult().error("用户名或密码错误，请重新输入");

    }

    @GetMapping("/getAllUser")
    @ApiOperation("用户信息  --- 获取所有用户")
    public AjaxResult getAllUsert(
            @RequestParam @ApiParam("当前页") Object page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("limit", 10);
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return new AjaxResult().error("参数异常");
        }
        pageUtil = new PageQueryUtil(params);
        pageResult = userService.getAllUser(pageUtil);
        if (pageResult != null) {
            return new AjaxResult().ok(pageResult);
        }
        return new AjaxResult().error("数据库用户表无信息");
    }

    @GetMapping("/getUserInfoById")
    @ApiOperation("用户信息 --- 查看用户信息")
    public AjaxResult getUserInfoById(@RequestParam("openId") @ApiParam("当前用户的openId") String openId) {
        UserDetailVo userDetailVo = userService.getUserByOpenId(openId);
        if (userDetailVo != null) {
            return new AjaxResult().ok(userDetailVo);
        }
        return new AjaxResult().error("该用户不存在");
    }

    @DeleteMapping("/deleteUser")
    @ApiOperation("用户信息 --- 注销用户信息")
    public AjaxResult deleteUserById(@RequestParam @ApiParam("用户的openId") String openId) {
        int res = userService.deleteUserById(openId);
        if (res > 0) {
            return new AjaxResult().ok("销户成功，用户信息已经删除");
        }
        return new AjaxResult().error("销户失败");
    }

    @GetMapping("/searchUserInfo")
    @ApiOperation("用户信息 --- 根据昵称搜索")
    public AjaxResult getAllUsert(@RequestParam @ApiParam("用户昵称") String userName) {
        UserDetailVo userDetailVo = userService.searchUserByName(userName);
        if (userDetailVo != null) {
            return new AjaxResult().ok(userDetailVo);
        }
        return new AjaxResult().error("你所输入的用户昵称不存在");
    }

    @GetMapping("UserIssueList")
    @ApiOperation("发布情况 --- 用户发布信息的列表")
    public AjaxResult getUserIssue(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("当前页") Integer page) {
        List<PostDetailVo> postDetailVoList = userService.getUserIssue(openId);
        if (postDetailVoList.size() > 10) {
            List<PostDetailVo> list = new ArrayList<PostDetailVo>();
            int pageStart = (page - 1) * 10;
            list = postDetailVoList.subList(pageStart, postDetailVoList.size() - pageStart > 10 ? pageStart + 10 : postDetailVoList.size());
            pageResult = new PageResult(list, postDetailVoList.size(), 10, page);
        }
        if (postDetailVoList != null) {
            return new AjaxResult().ok(pageResult);
        }
        return new AjaxResult().error("该用户没有发布任何招聘信息");
    }

    @DeleteMapping("deleteUserIssue")
    @ApiOperation("发布情况 --- 删除用户的发布消息")
    public AjaxResult deleteUserIssue(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("招聘信息ID") Integer postId) {
        int result = userService.deleteUserIssue(openId, postId);
        if (result > 0) {
            return new AjaxResult().ok("该发布信息删除成功");
        }
        return new AjaxResult().error("删除失败，传入的参数有误");
    }

    @GetMapping("UserCollectList")
    @ApiOperation("收藏情况 --- 查看用户的收藏")
    public AjaxResult getUserCollection(
            @RequestParam @ApiParam("当前用户的openId") String openId,
            @RequestParam @ApiParam("当前页") Integer page) {
        List<CollectPostDetailVo> postDetailVoList = userService.getUserCollection(openId);
        if (postDetailVoList.size() > 10) {
            List<CollectPostDetailVo> list = new ArrayList<CollectPostDetailVo>();
            int pageStart = (page - 1) * 10;
            list = postDetailVoList.subList(pageStart, postDetailVoList.size() - pageStart > 10 ? pageStart + 10 : postDetailVoList.size());
            pageResult = new PageResult(list, postDetailVoList.size(), 10, page);
        }
        if (postDetailVoList != null) {
            return new AjaxResult().ok(pageResult);
        }
        return new AjaxResult().error("该用户没有收藏任何东西");
    }

    @GetMapping("UserAskReply")
    @ApiOperation("问一问 ---  问答列表")
    public AjaxResult getUserAskReply(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("当前页") Integer page) {
        List<MyAskReplyVo> myAskReplyVoList = askReplyService.getMyAskReplyList(openId);
        if (myAskReplyVoList != null) {
            List<MyAskReplyVo> list = new ArrayList<MyAskReplyVo>();
            int pageStart = (page - 1) * 10;
            list = myAskReplyVoList.subList(pageStart, myAskReplyVoList.size() - pageStart > 10 ? pageStart + 10 : myAskReplyVoList.size());
            pageResult = new PageResult(list, myAskReplyVoList.size(), 10, page);
            return new AjaxResult().ok(pageResult);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

    @DeleteMapping("delAskReply")
    @ApiOperation("问一问 --- 删除评论  ")
    public AjaxResult deleteComment(
            @RequestParam @ApiParam("用户openId") String openId,
            @RequestParam @ApiParam("postId") Integer postId) {
        int result = askReplyService.deleteTbComment(openId,postId);
        if (result > 0) {
            return new AjaxResult().ok("评论信息删除成功");
        }
        return new AjaxResult().error("该评论删除失败，请确认是否操作出错");
    }

    @ApiOperation("后台 -- 查看招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(
            @RequestParam @ApiParam("招聘信息Id") Integer postId,
            @RequestParam @ApiParam("当前用户Id") String openId) {
        PostDetailsVo postDetailsVo = tbPostService.selectDetailByPrimaryKey(postId, openId,null);
        if (postDetailsVo != null) {
            return new AjaxResult().ok(postDetailsVo);
        }
        return new AjaxResult().error("查询失败");
    }

}

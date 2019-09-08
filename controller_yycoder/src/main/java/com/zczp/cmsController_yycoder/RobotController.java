package com.zczp.cmsController_yycoder;

import com.alibaba.fastjson.JSONObject;
import com.zczp.entity.TbPostWithBLOBs;

import com.zczp.service_cancer.Impl.TbCommentServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbReliabilityServiceImpl;
import com.zczp.service_yycoder.impl.UserServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_yycoder.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("robot")
@Api(tags = "内部账号管理界面")
public class RobotController {

    @Autowired
    private TbCommentServiceImpl tbCommentService;

    @Autowired
    private TbReliabilityServiceImpl tbReliabilityService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TbPostServiceImpl tbPostService;


    @GetMapping("/getAllRobotUser")
    @ApiOperation("用户信息  --- 获取所有虚拟用户")
    public AjaxResult getAllRobotUser() {
//            @RequestParam @ApiParam("当前页") Integer page
        List<UserDetailVo> userDetailVoList = userService.getAllUser(1);
//        if (userDetailVoList != null) {
//            List<UserDetailVo> list = new ArrayList<UserDetailVo>();
//            int pageStart = (page - 1) * 10;
//            list = userDetailVoList.subList(pageStart, userDetailVoList.size() - pageStart > 10 ? pageStart + 10 : userDetailVoList.size());
//            pageResult = new PageResult(list, userDetailVoList.size(), 10, page);
//            return new AjaxResult().ok(pageResult);
//        }
        if (userDetailVoList != null)
            return new AjaxResult().ok(userDetailVoList);
        return new AjaxResult().error("数据库用户表无信息");
    }

    @PostMapping("addRobotUser")
    @ApiOperation("用户信息  --- 添加虚拟用户")
    public AjaxResult addRobotUser(@ModelAttribute UserInfoVo userInfoVo) throws IOException {
        String userInfoStr = JSONObject.toJSONString(userInfoVo);
        UserDetailVo userDetailVo = JSONObject.parseObject(userInfoStr, UserDetailVo.class);
        int result = userService.addRobotUserIfo(userDetailVo);
        if (result > 0) {
            return new AjaxResult().ok("新增成功");
        }
        return new AjaxResult().error("新增失败");
    }

    @PutMapping("updateRobotUser")
    @ApiOperation("用户信息  ---   修改虚拟用户")
    public AjaxResult updateRobotUser(
            @RequestParam @ApiParam("用户的openId") String openId,
            @ModelAttribute UserInfoVo userInfoVo) throws IOException {
        String userInfoStr = JSONObject.toJSONString(userInfoVo);
        UserDetailVo userDetailVo = JSONObject.parseObject(userInfoStr, UserDetailVo.class);
        userDetailVo.setOpenId(openId);
        //访问的具体网址
        int result = userService.updateUserIfoById(userDetailVo);
        if (result > 0) {
            return new AjaxResult().ok("更新成功");
        }
        return new AjaxResult().error("更新失败");
    }


@ApiOperation("发布招聘岗位")
@PostMapping("/pushPost")
public AjaxResult pushPost(
        @RequestParam @ApiParam("岗位类型Id") Integer typeId,
        @RequestParam @ApiParam("公司Id") Integer companyId,
        @RequestParam @ApiParam("招聘类型（实习 校招）") String jobType,
        @RequestParam @ApiParam("城市Id") Integer cityId,
        @RequestParam(required = false) @ApiParam("入职时间") String entryTime,
        @RequestParam(required = false) @ApiParam("实习时长") String internshipTime,
        @RequestParam @ApiParam("投递方式") String deliveryMethod,
        @RequestParam @ApiParam("标题") String title,
        @RequestParam @ApiParam("用户id") String openId,
        @RequestParam @ApiParam("岗位要求") String requirement,
        @RequestParam @ApiParam("招聘描述") String jobDescription,
        @RequestParam @ApiParam("工作地址") String workAddress
){
    TbPostWithBLOBs tbPostWithBLOBs =new TbPostWithBLOBs();
    tbPostWithBLOBs.setTypeId(typeId);
    tbPostWithBLOBs.setCompanyId(companyId);
    tbPostWithBLOBs.setJobType(jobType);
    tbPostWithBLOBs.setCityId(cityId);
    Date postTime = new Date();
    tbPostWithBLOBs.setPostTime(postTime);
    tbPostWithBLOBs.setEntryTime(entryTime);
    tbPostWithBLOBs.setInternshipTime(internshipTime);
    tbPostWithBLOBs.setDeliveryMethod(deliveryMethod);
    tbPostWithBLOBs.setTitle(title);
    tbPostWithBLOBs.setRequirement(requirement);
    tbPostWithBLOBs.setJobDescription(jobDescription);
    tbPostWithBLOBs.setWorkAddress(workAddress);
    tbPostWithBLOBs.setOpenId(openId);
    tbPostWithBLOBs.setReliability(0);
    tbPostWithBLOBs.setState(0);
    int result=tbPostService.insert(tbPostWithBLOBs);
    if(result==1){
        return new AjaxResult().ok("发布成功");
    }
    return new AjaxResult().error("发布失败");
}


    @ApiOperation("删除招聘信息")
    @DeleteMapping("/deletePost")
    public AjaxResult deletePost(@RequestParam int postId) {
        int result = tbPostService.deletePostById(postId);
        if (result == 1) {
            return new AjaxResult().ok("删除成功");
        }
        return new AjaxResult().error("操作失败");
    }

    @ApiOperation("评论")
    @PostMapping("/comment")
    public AjaxResult Comment(
            @RequestParam @ApiParam("招聘信息Id") Integer postId,
            @RequestParam @ApiParam("评论内容") String content,
            @RequestParam @ApiParam("评论来自用户") String fromId,
            @RequestParam @ApiParam("回复的用户ID") String toId,
            @RequestParam(required = false) @ApiParam("回复的评论ID") Integer replyId) {
        CommentVo commentVo = new CommentVo();
        commentVo.setPostId(postId);
        commentVo.setContent(content);
        commentVo.setFromId(fromId);
        commentVo.setToId(toId);
        Date commentTime = new Date();
        commentVo.setCommentTime(commentTime);
        commentVo.setReplyId(replyId);
        int result = tbCommentService.insert(commentVo);
        if (result == 1) {
            return new AjaxResult().ok("评论成功");
        }
        return new AjaxResult().error("评论失败");
    }

    @ApiOperation("修改可信度")
    @PutMapping("/reliability")
    public AjaxResult reliability(
            @RequestParam @ApiParam("0-取消 1-可信") int r,
            @RequestParam @ApiParam("招聘信息表Id") int postId,
            @RequestParam @ApiParam("用户Id") String openId) {
        if (r == 1) {
            tbReliabilityService.saveReliabilityState(openId, postId);
            return new AjaxResult().ok("点击可信");
        } else if (r == 0) {
            tbReliabilityService.delReliabilityState(openId, postId);
            return new AjaxResult().ok("取消可信");
        }
        return new AjaxResult().error("操作失败");
    }
}

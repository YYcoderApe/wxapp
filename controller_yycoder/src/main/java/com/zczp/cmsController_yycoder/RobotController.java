package com.zczp.cmsController_yycoder;

import com.alibaba.fastjson.JSONObject;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.Impl.TbCommentServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbReliabilityServiceImpl;
import com.zczp.service_cancer.TbPostService;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.FileService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.util.PageQueryUtil;
import com.zczp.util.PageResult;
import com.zczp.util.TokenUtil;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.PostDetailVo;
import com.zczp.vo_yycoder.UserDetailVo;
import com.zczp.vo_yycoder.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("robot")
@Api(tags = "自用户管理界面")
public class RobotController {

    @Value("${baseUploadUrl}")
    private String url;

    @Value("${qiniu.path}")
    private String Path;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private TbCommentServiceImpl tbCommentService;

    @Autowired
    private TbReliabilityServiceImpl tbReliabilityService;
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TbPostService tbPostService;

    @Autowired
    private AskReplyService askReplyService;

    PageQueryUtil pageUtil;

    PageResult pageResult;

    @GetMapping("/getAllRobotUser")
    @ApiOperation("用户信息  --- 获取所有虚拟用户")
    public AjaxResult getAllRobotUser(
            @RequestParam @ApiParam("当前页") Integer page) {
        List<UserDetailVo> userDetailVoList = userService.getAllUser(1);
        if (userDetailVoList != null) {
            List<UserDetailVo> list = new ArrayList<UserDetailVo>();
            int pageStart = (page - 1) * 10;
            list = userDetailVoList.subList(pageStart, userDetailVoList.size() - pageStart > 10 ? pageStart + 10 : userDetailVoList.size());
            pageResult = new PageResult(list, userDetailVoList.size(), 10, page);
            return new AjaxResult().ok(pageResult);
        }
        return new AjaxResult().error("数据库用户表无信息");
    }

    @PostMapping("addRobotUser")
    @ApiOperation("用户信息  --- 添加虚拟用户")
    public AjaxResult addRobotUser(
            @RequestParam(value = "file") @ApiParam("上传头像") MultipartFile upfile,
            @ModelAttribute UserInfoVo userInfoVo) throws IOException {
        File file = new File(url + upfile.getOriginalFilename());
        //将MulitpartFile文件转化为file文件格式
        upfile.transferTo(file);
        String userInfoStr = JSONObject.toJSONString(userInfoVo);
        UserDetailVo userDetailVo = JSONObject.parseObject(userInfoStr,UserDetailVo.class);

        //访问的具体网址
        userDetailVo.setUserImage(Path + "/" + fileService.uploadFile(file).get("imgName"));
        int result = userService.addRobotUserIfo(userDetailVo);
        if (result > 0) {
            return new AjaxResult().ok("新增成功");
        }
        return new AjaxResult().error("新增失败");
    }

    @PutMapping("updateRobotUser")
    @ApiOperation("用户信息  ---   修改虚拟用户")
    public AjaxResult updateRobotUser(
            @RequestParam(value = "file") @ApiParam("上传头像") MultipartFile upfile,
            @ModelAttribute UserInfoVo userInfoVo) throws IOException {
        File file = new File(url + upfile.getOriginalFilename());
        //将MulitpartFile文件转化为file文件格式
        upfile.transferTo(file);
        String userInfoStr = JSONObject.toJSONString(userInfoVo);
        UserDetailVo userDetailVo = JSONObject.parseObject(userInfoStr,UserDetailVo.class);

        //访问的具体网址
        userDetailVo.setUserImage(Path + "/" + fileService.uploadFile(file).get("imgName"));
        int result = userService.addRobotUserIfo(userDetailVo);
        if (result > 0) {
            return new AjaxResult().ok("新增成功");
        }
        return new AjaxResult().error("新增失败");
    }

    @GetMapping("/getRobotUserById")
    @ApiOperation("用户信息  --- 查看虚拟用户信息")
    public AjaxResult getRobotUserById(@RequestParam("openId") @ApiParam("当前用户的openId") String openId) {
        UserDetailVo userDetailVo = userService.getUserByOpenId(openId);
        if (userDetailVo != null) {
            return new AjaxResult().ok(userDetailVo);
        }
        return new AjaxResult().error("该用户不存在");
    }

    @DeleteMapping("/deleteRobotUser")
    @ApiOperation("用户信息 --- 注销用户信息")
    public AjaxResult deleteRobotUser(@RequestParam @ApiParam("用户的openId") String openId) {
        int res = userService.deleteUserById(openId);
        if (res > 0) {
            return new AjaxResult().ok("销户成功，用户信息已经删除");
        }
        return new AjaxResult().error("销户失败");
    }


    @PostMapping("/pushPostIssue")
    @ApiOperation("发布情况 --- 虚拟用户新增发布")
    public AjaxResult pushPostIssue(@ModelAttribute TbPostWithBLOBs tbPostWithBLOBs){
        int result=tbPostService.insert(tbPostWithBLOBs);
        if(result==1){
            return new AjaxResult().ok("发布成功");
        }
        return new AjaxResult().error("发布失败");
    }

    @GetMapping("RobotUserIssueList")
    @ApiOperation("发布情况 --- 虚拟用户发布信息的列表")
    public AjaxResult getRobotUserIssueList(
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

    @DeleteMapping("deleteRobotUserIssue")
    @ApiOperation("发布情况 --- 删除虚拟用户的发布消息")
    public AjaxResult deleteUserIssue(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("招聘信息ID") Integer postId) {
        int result = userService.deleteUserIssue(openId, postId);
        if (result > 0) {
            return new AjaxResult().ok("该发布信息删除成功");
        }
        return new AjaxResult().error("删除失败，传入的参数有误");
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
        int result = askReplyService.deleteTbComment(openId, postId);
        if (result > 0) {
            return new AjaxResult().ok("评论信息删除成功");
        }
        return new AjaxResult().error("该评论删除失败，请确认是否操作出错");
    }


    AjaxResult ajaxResult=new AjaxResult();
    @ApiOperation("招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(
            @RequestParam @ApiParam("页数") int pageNum,
            @RequestParam @ApiParam("招聘信息Id") int postId,
            @RequestParam @ApiParam ("当前用户Id") String openId){
        PostDetailsVo postDetailsVo =tbPostService.selectDetailByPrimaryKey(postId,openId,pageNum);
        if(postDetailsVo!=null){
            return ajaxResult.ok(postDetailsVo);
        }
        return ajaxResult.error("查询失败");
    }

    @ApiOperation("删除招聘信息")
    @DeleteMapping("/deletePost")
    public  AjaxResult deletePost(@RequestParam int postId){
        int result=tbPostService.deletePostById(postId);
        if (result==1){
            return ajaxResult.ok("删除成功");
        }
        return ajaxResult.error("操作失败");
    }

    @ApiOperation("评论")
    @PostMapping("/comment")
    public  AjaxResult Comment(@RequestBody CommentVo commentVo){
        int result=tbCommentService.insert(commentVo);
        if (result==1){
            return ajaxResult.ok("评论成功");
        }
        return ajaxResult.error("评论失败");
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/deleteComment")
    public  AjaxResult deleteComment(@RequestParam int commentId){
        int result=tbCommentService.deleteByCommentId(commentId);
        if (result==1){
            return ajaxResult.ok("删除成功");
        }
        return ajaxResult.error("操作失败");
    }

    @ApiOperation("修改可信度")
    @PutMapping("/reliability")
    public  AjaxResult reliability(
            @RequestParam @ApiParam("0-取消 1-可信") int r,
            @RequestParam @ApiParam("招聘信息表Id") int postId,
            @RequestParam @ApiParam ("用户Id") String openId){
        if (r==1){
            tbReliabilityService.saveReliabilityState(openId,postId);
            return ajaxResult.ok("点击可信");
        }else if (r==0){
            tbReliabilityService.delReliabilityState(openId,postId);
            return ajaxResult.ok("取消可信");
        }
        return ajaxResult.error("操作失败");
    }
}

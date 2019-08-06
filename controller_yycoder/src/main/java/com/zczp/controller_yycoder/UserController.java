package com.zczp.controller_yycoder;

import com.zczp.entity.TbUser;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_yycoder.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "个人信息模块")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AskReplyService askReplyService;

    @GetMapping("/index")
    @ApiOperation("展示用户个人信息")
    public AjaxResult getUserInfo(@RequestParam("openId") @ApiParam("当前用户的openId") String openId){
        List<UserDetailVo> userDetailVoList = userService.getUserByOpenId(openId);
        if(userDetailVoList!=null){
            return new AjaxResult().ok(userDetailVoList);
        }
        return new AjaxResult().error("该用户不存在");
    }

    @PutMapping("update")
    @ApiOperation("修改编辑用户个人信息")
    public AjaxResult UpdateUserInfo(@ModelAttribute UserDetailVo userDetailVo){
        int recode=userService.updateUserIfoById(userDetailVo);
        if(recode>0){
            return new AjaxResult().ok("修改成功");
        }
        return new AjaxResult().error("修改失败");
    }

    @GetMapping("MyCollection/index")
    @ApiOperation("查看个人的收藏")
    public AjaxResult getUserCollection(@RequestParam @ApiParam("当前用户的openId") String openId){
        List<CollectPostDetailVo> postDetailVoList=userService.getUserCollection(openId);
        if(postDetailVoList!=null){
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有收藏任何东西");
    }

    @DeleteMapping("MyCollection/delete")
    @ApiOperation("删除个人收藏")
    public AjaxResult deleteCollection(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("招聘信息ID") Integer postId){
        int res = userService.deleteCollection(openId, postId);
        if(res>0){
            return new AjaxResult().ok("该收藏删除成功");
        }
        return new AjaxResult().error("删除失败，传入的数据有误");
    }

    @GetMapping("MyIssue/index")
    @ApiOperation("查看自己的发布")
    public AjaxResult getUserIssue(@RequestParam @ApiParam("当前用户ID") String openId){
        List<PostDetailVo> postDetailVoList=userService.getUserIssue(openId);
        if(postDetailVoList!=null){
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有发布任何招聘信息");
    }

    @DeleteMapping("MyIssue/delete")
    @ApiOperation("删除个人发布的招聘信息")
    public AjaxResult deleteUserIssue(
            @RequestParam @ApiParam("当前用户ID") String openId,
            @RequestParam @ApiParam("招聘信息ID") Integer postId){
        int result = userService.deleteUserIssue(openId, postId);
        if(result>0){
            return new AjaxResult().ok("该发布信息删除成功");
        }
        return new AjaxResult().error("删除失败，传入的参数有误");
    }

    @GetMapping("Question/MyAskReply")
    @ApiOperation("查看我的问一问(我的问答)")
    public AjaxResult getUserAskReply(@RequestParam @ApiParam("当前用户ID") String openId){
        List<MyAskReplyVo> myAskReplyVoList=askReplyService.getMyAskReplyList(openId);
        if(myAskReplyVoList!=null){
            return new AjaxResult().ok(myAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

    @GetMapping("Question/MyReplyMsg")
    @ApiOperation("查看我的问一问(回复消息)")
    public AjaxResult getUserReplyMsg(@RequestParam @ApiParam("当前用户ID") String openId){
        List<MyAskReplyVo> myAskReplyVoList=askReplyService.getMyReplyMsgList(openId);

        if(myAskReplyVoList!=null){
            return new AjaxResult().ok(myAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

    @DeleteMapping("Question/deleteComment")
    @ApiOperation("查看我的问一问(回复消息)")
    public AjaxResult deleteComment(@RequestParam @ApiParam("评论的Id") Integer commentId){
        int result = askReplyService.deleteTbCommentBycommentId(commentId);
        if(result>0){
            return new AjaxResult().ok("评论信息删除成功");
        }
        return new AjaxResult().error("该评论删除失败，请确认是否操作出错");
    }
}

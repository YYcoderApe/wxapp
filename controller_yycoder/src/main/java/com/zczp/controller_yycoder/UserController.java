package com.zczp.controller_yycoder;

import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.util.TokenUtil;
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
    TokenUtil tokenUtil;

    @Autowired(required = false)
    private UserService userService;

    @Autowired
    private AskReplyService askReplyService;

    @GetMapping("/index")
    @ApiOperation("展示用户个人信息")
    public AjaxResult getUserInfo(@RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        UserDetailVo userDetailVo = userService.getUserByOpenId(openId);
        if (userDetailVo != null) {
            return new AjaxResult().ok(userDetailVo);
        }
        return new AjaxResult().error("该用户不存在");
    }


    @PostMapping("update")
    @ApiOperation("修改编辑用户个人信息")
    public AjaxResult UpdateUserInfo(
            @RequestParam @ApiParam("用户token") String token,
            @RequestParam(required = false) @ApiParam("用户性别") String userGender,
            @RequestParam(required = false) @ApiParam("用户院校") String userSchool,
            @RequestParam(required = false) @ApiParam("所在公司") String userCompany,
            @RequestParam(required = false) @ApiParam("职位类型") String userPosttype,
            @RequestParam(required = false) @ApiParam("联系方式") String userPhone,
            @RequestParam(required = false) @ApiParam("个性签名") String userSignature) {
        UserDetailVo userDetailVo = new UserDetailVo();
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        userDetailVo.setOpenId(openId);
        userDetailVo.setUserGender(userGender);
        userDetailVo.setUserSchool(userSchool);
        userDetailVo.setUserCompany(userCompany);
        userDetailVo.setUserPosttype(userPosttype);
        userDetailVo.setUserPhone(userPhone);
        userDetailVo.setUserSignature(userSignature);
        int recode = userService.updateUserIfoById(userDetailVo);
        if (recode > 0) {
            return new AjaxResult().ok("修改成功");
        }
        return new AjaxResult().error("修改失败");
    }

    @GetMapping("MyCollection/index")
    @ApiOperation("查看个人的收藏")
    public AjaxResult getUserCollection(@RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        List<CollectPostDetailVo> postDetailVoList = userService.getUserCollection(openId);
        if (postDetailVoList != null) {
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有收藏任何东西");
    }

    @PostMapping("MyCollection/delete")
    @ApiOperation("删除个人收藏")
    public AjaxResult deleteCollection(
            @RequestParam @ApiParam("招聘信息ID") Integer postId,
            @RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        int res = userService.deleteCollection(openId, postId);
        if (res > 0) {
            return new AjaxResult().ok("该收藏删除成功");
        }
        return new AjaxResult().error("删除失败，传入的数据有误");
    }

    @GetMapping("MyIssue/index")
    @ApiOperation("查看自己的发布")
    public AjaxResult getUserIssue(@RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        List<PostDetailVo> postDetailVoList = userService.getUserIssue(openId);
        if (postDetailVoList != null) {
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有发布任何招聘信息");
    }

    @PostMapping("MyIssue/delete")
    @ApiOperation("删除个人发布的招聘信息")
    public AjaxResult deleteUserIssue(
            @RequestParam @ApiParam("招聘信息ID") Integer postId,
            @RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        int result = userService.deleteUserIssue(openId, postId);
        if (result > 0) {
            return new AjaxResult().ok("该发布信息删除成功");
        }
        return new AjaxResult().error("删除失败，传入的参数有误");
    }

    @GetMapping("Question/MyAskReply")
    @ApiOperation("查看我的问一问(我的问答)")
    public AjaxResult getUserAskReply(@RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        List<MyAskReplyVo> myAskReplyVoList = askReplyService.getMyAskReplyList(openId);
        if (myAskReplyVoList != null) {
            return new AjaxResult().ok(myAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

    @GetMapping("Question/MyReplyMsg")
    @ApiOperation("查看我的问一问(回复消息)")
    public AjaxResult getUserReplyMsg(@RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        List<MyAskReplyVo> myAskReplyVoList = askReplyService.getMyReplyMsgList(openId);

        if (myAskReplyVoList != null) {
            return new AjaxResult().ok(myAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

    @DeleteMapping("Question/deleteComment")
    @ApiOperation("删除我的相关评论(指定的评论)")
    public AjaxResult deleteComment(@RequestParam @ApiParam("评论的Id") Integer commentId) {
        int result = askReplyService.deleteTbCommentBycommentId(commentId);
        if (result > 0) {
            return new AjaxResult().ok("评论信息删除成功");
        }
        return new AjaxResult().error("该评论删除失败，请确认是否操作出错");
    }

    @PostMapping("Question/delAskReply")
    @ApiOperation(" 删除评论(招聘信息已删除情况下)  ")
    public AjaxResult deleteComment(
            @RequestParam @ApiParam("postId") Integer postId,
            @RequestParam @ApiParam("token") String token) {
        String openId = tokenUtil.getOpenId(token);
        if (openId == null)
            return new AjaxResult().error("token失效，请重新输入");
        int result = askReplyService.deleteTbComment(openId, postId);
        if (result > 0) {
            return new AjaxResult().ok("评论信息删除成功");
        }
        return new AjaxResult().error("该评论删除失败，请确认是否操作出错");
    }

    @GetMapping("/other/userInfo")
    @ApiOperation("查看他人信息")
    public AjaxResult getOtherUserInfo(@RequestParam @ApiParam("用户fromId") String fromId) {
        UserDetailVo userDetailVo = userService.getUserByOpenId(fromId);
        if (userDetailVo != null) {
            return new AjaxResult().ok(userDetailVo);
        }
        return new AjaxResult().error("该用户不存在");
    }

    @GetMapping("other/UserIssue")
    @ApiOperation("查看他人的发布")
    public AjaxResult getOtherUserIssue(@RequestParam @ApiParam("用户fromId") String fromId) {
        List<PostDetailVo> postDetailVoList = userService.getUserIssue(fromId);
        if (postDetailVoList != null) {
            return new AjaxResult().ok(postDetailVoList);
        }
        return new AjaxResult().error("该用户没有发布任何招聘信息");
    }

    @GetMapping("other/UserAskReply")
    @ApiOperation("查看他人问答")
    public AjaxResult getOtherUserAskReply(@RequestParam @ApiParam("fromId") String fromId) {
        List<MyAskReplyVo> myAskReplyVoList = askReplyService.getMyAskReplyList(fromId);
        if (myAskReplyVoList != null) {
            return new AjaxResult().ok(myAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }

}

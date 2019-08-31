package com.zczp.cmsController_yycoder;

import com.zczp.service_cancer.TbCommentService;
import com.zczp.service_cancer.TbPostService;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.service_yycoder.UserService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.PostDetailVo;
import com.zczp.vo_yycoder.UserAskReplyVo;
import com.zczp.vo_yycoder.UserDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userInfo")
@Api(tags = "多处重复调用的接口")
public class CommonController {

    @Autowired
    private AskReplyService askReplyService;

    @Autowired
    private UserService userService;

    @Autowired
    private TbCommentService tbCommentService;

    @Autowired
    private TbPostService tbPostService;

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

    @GetMapping("UserAskReplyList")
    @ApiOperation("问一问 ---  问答列表")
    public AjaxResult getUserAskReply(@RequestParam @ApiParam("当前用户ID") String openId) {
        List<UserAskReplyVo> userAskReplyVoList = askReplyService.getUserAskReplyList(openId);
        if (userAskReplyVoList != null) {
            return new AjaxResult().ok(userAskReplyVoList);
        }
        return new AjaxResult().error("该用户没有在任何招聘信息上问答");
    }


    @ApiOperation("问一问 --- 删除评论")
    @DeleteMapping("/deleteComment")
    public AjaxResult deleteComment(@RequestParam int commentId) {
        int result = tbCommentService.deleteByCommentId(commentId);
        if (result == 1) {
            return new AjaxResult().ok("删除成功");
        }
        return new AjaxResult().error("操作失败");
    }
    @GetMapping("UserIssueList")
    @ApiOperation("发布情况 --- 用户发布信息的列表")
    public AjaxResult getUserIssue(@RequestParam @ApiParam("当前用户ID") String openId) {
        List<PostDetailVo> postDetailVoList = userService.getUserIssue(openId);
        if (postDetailVoList != null) {
            return new AjaxResult().ok(postDetailVoList);
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

    @ApiOperation("查看招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(
            @RequestParam @ApiParam("招聘信息Id") Integer postId,
            @RequestParam(required = false) @ApiParam("当前用户Id") String openId) {
        PostDetailsVo postDetailsVo = tbPostService.selectDetailByPrimaryKey(postId, openId, null);
        if (postDetailsVo != null) {
            return new AjaxResult().ok(postDetailsVo);
        }
        return new AjaxResult().error("查询失败");
    }


}

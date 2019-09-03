package com.zczp.cmsController;

import com.zczp.service_cancer.Impl.TbCommentServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbReliabilityServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.TokenUtil;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.PostDetailsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@Api(tags = "招聘信息模块")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    private TbPostServiceImpl tbPostService;
    @Autowired
    private TbCommentServiceImpl tbCommentService;
    @Autowired
    private TbReliabilityServiceImpl tbReliabilityService;

    AjaxResult ajaxResult=new AjaxResult();
    @ApiOperation("招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(
            @RequestParam @ApiParam("招聘信息Id") int postId,
            @RequestParam(required = false) @ApiParam ("当前用户Id") String openId){
        PostDetailsVo postDetailsVo =tbPostService.selectDetailByPrimaryKey(postId,openId,null);
        if(postDetailsVo!=null){
            return ajaxResult.ok(postDetailsVo);
        }
        return ajaxResult.error("查询失败,没有此信息");
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
    public  AjaxResult Comment(
            @RequestParam  @ApiParam("招聘信息Id") Integer postId,
            @RequestParam  @ApiParam("内容") String content,
            @RequestParam  @ApiParam("用户id") String openId,
            @RequestParam  @ApiParam("回复的用户ID") String toId,
            @RequestParam(required = false)  @ApiParam("回复的评论ID") Integer replyId
    ){
        CommentVo commentVo=new CommentVo();
        commentVo.setPostId(postId);
        commentVo.setContent(content);
        commentVo.setFromId(openId);
        commentVo.setToId(toId);
        Date commentTime=new Date();
        commentVo.setCommentTime(commentTime);
        commentVo.setReplyId(replyId);
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

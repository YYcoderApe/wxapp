package com.zczp.controller_cancer;

import com.zczp.entity.*;
import com.zczp.service_cancer.Impl.TbCollectServiceImpl;
import com.zczp.service_cancer.Impl.TbCommentServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbReliabilityServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.PostDetailsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "招聘信息模块")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private TbPostServiceImpl tbPostService;
    @Autowired
    private TbCommentServiceImpl tbCommentService;
    @Autowired
    private TbCollectServiceImpl tbCollectService;
    @Autowired
    private TbReliabilityServiceImpl tbReliabilityService;

    AjaxResult ajaxResult=new AjaxResult();
    @ApiOperation("招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(
            @RequestParam @ApiParam("招聘信息Id") int postId,
            @RequestParam @ApiParam ("当前用户Id") String openId){
        PostDetailsVo postDetailsVo =tbPostService.selectDetailByPrimaryKey(postId,openId);
        return ajaxResult.ok(postDetailsVo);
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
    //未完成
    @ApiOperation("修改可信度")
    @PostMapping("/reliability")
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

    @ApiOperation("收藏招聘信息")
    @PostMapping("/collect")
    public  AjaxResult collect(
            @RequestParam @ApiParam("0-取消收藏 1-收藏") int collect,
            @RequestParam @ApiParam("招聘信息表Id") int postId,
            @RequestParam @ApiParam("用户ID" ) String openId)
    {
        if (collect==1){
            tbCollectService.saveCollectState(postId,openId);
            return ajaxResult.ok("点击收藏");
        }else if (collect==0){
            tbCollectService.delCollectState(postId,openId);
            return ajaxResult.ok("取消收藏");
        }
        return ajaxResult.error("操作失败");
    }

//    @ApiOperation("生成海报")
//    @GetMapping("/poster")
//    public  AjaxResult poster(){
//        return null;
//    }
}

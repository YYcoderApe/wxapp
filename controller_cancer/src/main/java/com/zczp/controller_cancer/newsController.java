package com.zczp.controller_cancer;

import com.zczp.service_cancer.Impl.TbCommentServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "消息提示")
@RequestMapping("/api/news")
public class newsController {
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private TbCommentServiceImpl tbCommentService;
    @GetMapping("/newsCount")
    public AjaxResult newsCount(@RequestParam @ApiParam("用户token") String token){
        String openId=tokenUtil.getOpenId(token);
        if (openId==null) return ajaxResult.error("token失效");
        String count=tbCommentService.getNewsCount(openId);
        if (count!=null){
            return ajaxResult.ok(count);
        }else {
            return ajaxResult.ok("没有新消息");
        }
    }

    @DeleteMapping("/delNewsCount")
    public AjaxResult delNewsCount(@RequestParam @ApiParam("用户token") String token){
        String openId=tokenUtil.getOpenId(token);
        if (openId==null) return ajaxResult.error("token失效");
        int result=tbCommentService.delNewsCount(openId);
        if (result==1){
            return ajaxResult.ok("删除成功");
        }
        return ajaxResult.ok("没有消息");
    }
}

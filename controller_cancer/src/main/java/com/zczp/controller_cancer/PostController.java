package com.zczp.controller_cancer;

import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "招聘信息模块")
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private TbPostServiceImpl tbPostService;
    @ApiOperation("招聘信息详情")
    @GetMapping("/postDetail")
    public AjaxResult postDetail(@RequestParam int post_id){
        AjaxResult ajaxResult=new AjaxResult();
        TbPostWithBLOBs tbPostWithBLOBs=tbPostService.selectByPrimaryKey(post_id);
        return ajaxResult.ok(tbPostWithBLOBs);
    }
//    @ApiOperation("招聘信息详情")
//    @GetMapping("/postDetail")
//    public TbPostWithBLOBs postDetail(@RequestParam int post_id){
////        AjaxResult ajaxResult=new AjaxResult();
//        TbPostWithBLOBs tbPostWithBLOBs=tbPostService.selectByPrimaryKey(post_id);
//        return tbPostWithBLOBs;
//    }
}

package com.zczp.controller_yycoder;

import com.zczp.service_yycoder.HomeService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_yycoder.PostDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "主页信息模块")
@RequestMapping("home")
public class HomeController {


    @Autowired
    private HomeService homeService;

    //主页界面展示（）
    @GetMapping(value="/index")
    @ApiOperation("主页展示的信息详情")
    public AjaxResult getAllPost() {
        List<PostDetailVo> postDetailVos = homeService.getPostDetail();
        if(postDetailVos!=null){
            return new AjaxResult().ok(postDetailVos);
        }
        return new AjaxResult().error("库存中表post没数据或信息待审核");
    }

    @GetMapping("select")
    @ApiOperation("根据条件进行展示招聘信息")
    public AjaxResult getPostByName(
            @RequestParam("cityName") String cityName,
            @RequestParam("jobType") String jobType,
            @RequestParam("postType") String postType) {
        List<PostDetailVo> postDetailVos = homeService.getPostByCityName(cityName,jobType,postType);
        if(postDetailVos!=null){
            return new AjaxResult().ok(postDetailVos);
        }
        return new AjaxResult().error("你输入的信息有误");
    }


}

package com.zczp.controller_yycoder;

import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.service_yycoder.HomeService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Api(tags = "主页信息模块")
@RequestMapping("home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value = "/index")
    @ApiOperation("主页展示的信息详情")
    public AjaxResult getAllPost() {
        List<PostDetailVo> postDetailVos = homeService.getPostDetail();
        if (postDetailVos != null) {
            return new AjaxResult().ok(postDetailVos);
        }
        return new AjaxResult().error("库存中表post没数据或信息待审核");
    }

    //    @GetMapping("select")
//    @ApiOperation("根据条件进行展示招聘信息")
//    public AjaxResult getPostByName(
//            @RequestParam("cityName") String cityName,
//            @RequestParam("jobType") String jobType,
//            @RequestParam("postType") String postType) {
//        List<PostDetailVo> postDetailVos = homeService.getPostByCondition(cityName,jobType,postType);
//        if(postDetailVos!=null){
//            return new AjaxResult().ok(postDetailVos);
//        }
//        return new AjaxResult().error("你输入的信息有误");
//    }
    @GetMapping("select")
    @ApiOperation("根据条件进行展示招聘信息")
    public AjaxResult getPostByCondition(@ModelAttribute ConditionVo conditionVo) {
        List<PostDetailVo> postDetailVos = homeService.getPostByCondition(conditionVo);
        if (postDetailVos != null) {
            return new AjaxResult().ok(postDetailVos);
        }
        return new AjaxResult().error("你输入的信息有误");
    }

    @GetMapping("city")
    @ApiOperation("获取城市的分类")
    public AjaxResult getCitySort() {
        List<TbCity> tbCityList = homeService.getAllCitySort();
        if (tbCityList != null) {
            return new AjaxResult().ok(tbCityList);
        }
        return new AjaxResult().error("数据库中没有相应的城市分类");
    }

    @GetMapping("postType")
    @ApiOperation("获取招聘类型")
    public AjaxResult getPostTypeSort() {
        Map<String,Object> map = new HashMap<>();
        map.put("jobType1","实习");
        map.put("jobType2","校招");
        return new AjaxResult().ok(map);
    }

    @GetMapping("jobType")
    @ApiOperation("获取职位类型")
    public AjaxResult getJobTypeSort() {
        List<TbPostType> tbPostTypeList = homeService.getAllJobTypeSort();
        if (tbPostTypeList != null) {
            return new AjaxResult().ok(tbPostTypeList);
        }
        return new AjaxResult().error("数据库中没有职位类型");
    }
}

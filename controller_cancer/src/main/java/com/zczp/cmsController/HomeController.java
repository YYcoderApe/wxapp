package com.zczp.cmsController;

import com.github.pagehelper.PageHelper;
import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.cmsService.HomeService;
import com.zczp.util.AjaxResult;
import com.zczp.vo_yycoder.PostDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Api(tags = "信息管理模块")
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @Autowired
    private TbPostServiceImpl tbPostService;
    AjaxResult ajaxResult=new AjaxResult();
    @GetMapping(value="/index")
    @ApiOperation("分页展示信息列表")
    public AjaxResult getAllPost(@RequestParam @ApiParam("页数") int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<PostDetailVo> postDetailVos = homeService.getPostDetail();
        if(postDetailVos!=null){
            return ajaxResult.ok(postDetailVos);
        }
        return new AjaxResult().error("库存中表post没数据或信息待审核");
    }

    @ApiOperation("查询招聘岗位")
    @GetMapping("/searchPost")
    public AjaxResult searchPost(@RequestParam @ApiParam("公司名称") String company){
        List<PostDetailVo> postDetailVoList=tbPostService.selectByCompany(company);
        if (postDetailVoList!=null){
            return ajaxResult.ok(postDetailVoList);
        }
        return ajaxResult.error("没有该招聘信息");
    }
    @GetMapping(value="/delete")
    @ApiOperation("删除招聘信息")
    public AjaxResult delete(@RequestParam int postId) {
        Integer result = homeService.deleteByPrimaryKey(postId);
        if (result==0){
            return ajaxResult.ok("操作失败");
        }
        return ajaxResult.ok("删除成功");
    }

    @GetMapping("/select")
    @ApiOperation("根据条件进行展示招聘信息")
    public AjaxResult getPostByName(
//            @RequestParam int pagenum,
            @RequestParam("cityName") String cityName,
            @RequestParam("jobType") String jobType,
            @RequestParam("postType") String postType
//            @RequestParam String companyName
            ) {
//        PageHelper.startPage(pagenum,10);
        List<PostDetailVo> postDetailVos = homeService.getPostByCondition(cityName,jobType,postType);
        if(postDetailVos!=null){
            return new AjaxResult().ok(postDetailVos);
        }
        return new AjaxResult().error("你输入的信息有误");
    }

    @GetMapping("/city")
    @ApiOperation("获取城市的分类")
    public AjaxResult getCitySort(){
        List<TbCity> tbCityList = homeService.getAllCitySort();
        if(tbCityList!=null){
            return new AjaxResult().ok(tbCityList);
        }
        return new AjaxResult().error("数据库中没有相应的城市分类");
    }

    @GetMapping("/postType")
    @ApiOperation("获取招聘类型")
    public  AjaxResult getPostTypeSort(){
        List<String> list = new ArrayList<String>();
        list.add("实习");
        list.add("校招");
        return new AjaxResult().ok(list);
    }

    @GetMapping("/jobType")
    @ApiOperation("获取职位类型")
    public AjaxResult getJobTypeSort(){
        List<TbPostType> tbPostTypeList = homeService.getAllJobTypeSort();
        if(tbPostTypeList!=null){
            return new AjaxResult().ok(tbPostTypeList);
        }
        return new AjaxResult().error("数据库中没有职位类型");
    }
}

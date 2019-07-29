package com.zczp.controller_cancer;

import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.Impl.TbCityServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "发布招聘信息模块")
@RequestMapping("/api/pushPost")
public class PushController {
    @Autowired
    private TbPostServiceImpl tbPostService;
    @Autowired
    private TbCityServiceImpl tbCityService;
    AjaxResult ajaxResult=new AjaxResult();
    @ApiOperation("发布招聘岗位")
    @PostMapping("/pushPost")
    public AjaxResult pushPost(@RequestBody TbPostWithBLOBs tbPostWithBLOBs){
        int result=tbPostService.insert(tbPostWithBLOBs);
        if(result==1){
            return ajaxResult.ok("发布成功");
        }
        return ajaxResult.error("发布失败");
    }

    @ApiOperation("开放城市")
    @GetMapping("/city")
    public AjaxResult searchCity(){
        return ajaxResult.ok(tbCityService.selectAll());
    }

    @ApiOperation("搜索公司")
    @GetMapping("/searchCompany")
    public AjaxResult searchCompany(){
        return null;
    }

    @ApiOperation("展示搜索前12名")
    @GetMapping("/showCompany")
    public AjaxResult showCompany(){
        return null;
    }
}

package com.zczp.controller_cancer;

import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "发布招聘信息模块")
@RequestMapping("/api/pushPost")
public class PushController {

    @ApiOperation("发布招聘岗位")
    @PostMapping("/pushPost")
    public AjaxResult pushPost(){
        return null;
    }

    @ApiOperation("开放城市")
    @GetMapping("/city")
    public AjaxResult searchCity(){
        return null;
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

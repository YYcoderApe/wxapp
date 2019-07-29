package com.zczp.controller_cancer;

import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "搜索功能模块")
@RequestMapping("/api/search")
public class SearchController {

    @ApiOperation("查询招聘岗位")
    @GetMapping("/searchPost")
    public AjaxResult searchPost(){
        return null;
    }

    @ApiOperation("历史记录")
    @GetMapping("/history")
    public AjaxResult history(){
        return null;
    }

    @ApiOperation("清空历史记录")
    @GetMapping("/deleteHistory")
    public AjaxResult deleteHistory(){
        return null;
    }
}

package com.zczp.controller_cancer;

import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.RedisUtil;
import com.zczp.vo_yycoder.PostDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "搜索功能模块")
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private TbPostServiceImpl tbPostService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AjaxResult ajaxResult;
    @ApiOperation("查询招聘岗位")
    @GetMapping("/searchPost")
    public AjaxResult searchPost(@RequestParam @ApiParam("公司名称") String company){
        List<PostDetailVo> postDetailVoList=tbPostService.selectByCompanyAndState(company);
        if (!postDetailVoList.isEmpty()){
            return ajaxResult.ok(postDetailVoList);
        }
        return ajaxResult.error("没有该招聘信息");
    }

//    @ApiOperation("历史记录")
//    @GetMapping("/history")
//    public AjaxResult history(){
//        List<String> list=tbPostService.getSearchHistory();
//        return ajaxResult.ok(list);
//    }
//
//    @ApiOperation("清空历史记录")
//    @GetMapping("/deleteHistory")
//    public AjaxResult deleteHistory(){
//        tbPostService.deleteHistory();
//        return ajaxResult.ok("删除成功");
//    }
}

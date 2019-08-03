package com.zczp.controller_cancer;

import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.Impl.TbCityServiceImpl;
import com.zczp.service_cancer.Impl.TbCompanyServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbPostTypeServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "发布招聘信息模块")
@RequestMapping("/api/pushPost")
public class PushController {
    @Autowired
    private TbPostServiceImpl tbPostService;
    @Autowired
    private TbCityServiceImpl tbCityService;
    @Autowired
    private TbCompanyServiceImpl tbCompanyService;
    @Autowired
    private TbPostTypeServiceImpl tbPostTypeService;
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
    public List<TbCity> searchCity(){
       return tbCityService.selectAll();
    }

    @ApiOperation("职业类型")
    @GetMapping("/postType")
    public List<TbPostType> searchPostType(){
        return tbPostTypeService.selectAll();
    }

    @ApiOperation("搜索公司")
    @GetMapping("/searchCompany")
    public AjaxResult searchCompany(@RequestParam @ApiParam("公司名") String companyName){
        List<CompanyVo> companyVos = tbCompanyService.selectByName(companyName);
        if (companyVos!=null){
            return ajaxResult.ok(companyVos);
        }
        return ajaxResult.error("没有此公司");
    }

    @ApiOperation("展示搜索前12名")
    @GetMapping("/showCompany")
    public List<CompanyVo> showCompany(){
        return tbCompanyService.selectByCount();
    }
}

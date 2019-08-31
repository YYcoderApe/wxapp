package com.zczp.controller_cancer;

import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.Impl.TbCityServiceImpl;
import com.zczp.service_cancer.Impl.TbCompanyServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.service_cancer.Impl.TbPostTypeServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.TokenUtil;
import com.zczp.vo_cancer.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private TokenUtil tokenUtil;
//    @ApiOperation("发布招聘岗位")
//    @PostMapping("/pushPost")
//    public AjaxResult pushPost(@RequestBody TbPostWithBLOBs tbPostWithBLOBs){
//        int result=tbPostService.insert(tbPostWithBLOBs);
//        if(result==1){
//            return ajaxResult.ok("发布成功");
//        }
//        return ajaxResult.error("发布失败");
//    }

    @ApiOperation("发布招聘岗位")
    @PostMapping("/pushPost")
    public AjaxResult pushPost(
            @RequestParam @ApiParam("岗位类型Id") Integer typeId,
            @RequestParam @ApiParam("公司Id") Integer companyId,
            @RequestParam @ApiParam("招聘类型（实习 校招）") String jobType,
            @RequestParam @ApiParam("城市Id") Integer cityId,
            @RequestParam @ApiParam("发布时间yyyy-MM-dd HH:mm:ss") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date postTime,
            @RequestParam(required = false) @ApiParam("入职时间") String entryTime,
            @RequestParam(required = false) @ApiParam("实习时长") String internshipTime,
            @RequestParam @ApiParam("投递方式") String deliveryMethod,
            @RequestParam @ApiParam("标题") String title,
            @RequestParam @ApiParam("用户token") String token,
            @RequestParam @ApiParam("岗位要求") String requirement,
            @RequestParam @ApiParam("招聘描述") String jobDescription,
            @RequestParam @ApiParam("工作地址") String workAddress
            ){
        String openId=tokenUtil.getOpenId(token);
        if (openId==null) return ajaxResult.error("token失效");
        TbPostWithBLOBs tbPostWithBLOBs =new TbPostWithBLOBs();
        tbPostWithBLOBs.setTypeId(typeId);
        tbPostWithBLOBs.setCompanyId(companyId);
        tbPostWithBLOBs.setJobType(jobType);
        tbPostWithBLOBs.setCityId(cityId);
        tbPostWithBLOBs.setPostTime(postTime);
        tbPostWithBLOBs.setEntryTime(entryTime);
        tbPostWithBLOBs.setInternshipTime(internshipTime);
        tbPostWithBLOBs.setDeliveryMethod(deliveryMethod);
        tbPostWithBLOBs.setTitle(title);
        tbPostWithBLOBs.setRequirement(requirement);
        tbPostWithBLOBs.setJobDescription(jobDescription);
        tbPostWithBLOBs.setWorkAddress(workAddress);
        tbPostWithBLOBs.setOpenId(openId);
        tbPostWithBLOBs.setReliability(0);
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
        if (!companyVos.isEmpty()){
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

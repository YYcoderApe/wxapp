package com.zczp.cmsController;

import com.github.pagehelper.PageHelper;
import com.zczp.service_cancer.Impl.TbCompanyServiceImpl;
import com.zczp.service_yycoder.PosterService;
import com.zczp.service_yycoder.impl.FileServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.util.PageResult;
import com.zczp.vo_cancer.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "公司管理模块")
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private TbCompanyServiceImpl tbCompanyService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private FileServiceImpl fileService;

//    PageResult pageResult;
//    //每次展示条数
//    int pageSize=10;
    @ApiOperation("展示所以公司")
    @GetMapping("/showCompany")
    public AjaxResult showComopany(){
//        PageHelper.startPage(pageNum,pageSize);
        List<CompanyVo> companyVos=tbCompanyService.selectAll();
//        int totalTags=tbCompanyService.getTotalTags();
        if (!companyVos.isEmpty()){
//            pageResult=new PageResult(companyVos,totalTags,pageSize,pageNum);
            return ajaxResult.ok(companyVos);
        }
        return ajaxResult.error("操作失败");
    }

    @ApiOperation("搜索公司")
    @GetMapping("/search")
    public AjaxResult searchCompany(
            @RequestParam @ApiParam("公司名称")String companyName){

//        PageHelper.startPage(pageNum,pageSize);
        List<CompanyVo> companyVos=tbCompanyService.selectByName(companyName);
//        int totalTags=tbCompanyService.getSearchTags(companyName);
        if (!companyVos.isEmpty()){
//            pageResult=new PageResult(companyVos,totalTags,pageSize,pageNum);
            return ajaxResult.ok(companyVos);
        }
        return ajaxResult.ok("没有此公司");
    }

    @ApiOperation("新增公司")
    @PostMapping("/addCompany")
    public AjaxResult addCompany(
            @RequestParam @ApiParam("公司logoUrl") String companyLogoUrl ,
            @RequestParam @ApiParam("公司名称") String companyName) {
//        File file = new File(url + upfile.getOriginalFilename());
        Map<String,Object> map = new HashMap<>();
//        //将MulitpartFile文件转化为file文件格式
//        upfile.transferTo(file);
        CompanyVo companyVo=new CompanyVo();
        companyVo.setCompanyLogo(companyLogoUrl);
        companyVo.setCompanyName(companyName);
        Integer result=tbCompanyService.addCompany(companyVo);
        System.out.println(result);
        if (result>0){
            map.put("公司logoUrl", companyVo.getCompanyLogo());
            map.put("公司名称",companyVo.getCompanyName());
            map.put("state","SUCESS,公司新增成功");

            return new AjaxResult().ok(map);
        }
        return new AjaxResult().error("新增失败");
    }

    @ApiOperation("编辑公司")
    @PutMapping("/updateCompany")
    public AjaxResult updateCompany(
            @RequestParam @ApiParam("公司Id")int companyId,
            @RequestParam @ApiParam("公司名称")String companyName,
            @RequestParam @ApiParam("公司logoUrl") String companyLogoUrl){
        Map<String,Object> map = new HashMap<>();
//        File file = new File(url + upfile.getOriginalFilename());
//        //将MulitpartFile文件转化为file文件格式
//        upfile.transferTo(file);
        CompanyVo companyVo=new CompanyVo();
        companyVo.setCompanyLogo(companyLogoUrl);
        companyVo.setCompanyName(companyName);
        companyVo.setCompanyId(companyId);
        Integer result=tbCompanyService.updateCompany(companyVo);
        if (result!=0){
            map.put("公司logoUrl", companyVo.getCompanyLogo());
            map.put("公司名称",companyVo.getCompanyName());
            map.put("state","SUCESS,公司编辑成功");
            return new AjaxResult().ok(map);
        }
        return new AjaxResult().error("编辑失败");
    }

    @ApiOperation("删除公司")
    @DeleteMapping("/deleteCompany")
    public AjaxResult deleteCompany(
            @RequestParam @ApiParam("公司Id")int companyId){
        Integer result=tbCompanyService.deleteCompanyById(companyId);
        if (result==1){
            return ajaxResult.ok("删除成功");
        }
        return ajaxResult.ok("操作失败");
    }

    @ApiOperation("Execl导入公司")
    @PostMapping("/importCompany")
    public AjaxResult importCompany(
            @RequestParam @ApiParam("execl文件")MultipartFile file){
        tbCompanyService.importCompany(file);
        return ajaxResult.ok("成功");
    }
}

package com.zczp.cmsController;

import com.github.pagehelper.PageHelper;
import com.zczp.service_cancer.Impl.TbCompanyServiceImpl;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Api(tags = "公司管理模块")
@RequestMapping("/api/company")
public class CompanyController {
    @Value("${baseUploadUrl}")
    private String url;
    @Value("${qiniu.path}")
    private String Path;
    @Autowired
    private TbCompanyServiceImpl tbCompanyService;
    @Autowired
    private AjaxResult ajaxResult;
//    @Autowired
//    private FileServiceImpl fileService;

    @ApiOperation("展示所以公司")
    @GetMapping("/showCompany")
    public AjaxResult showComopany(
            @RequestParam @ApiParam("页数")int pageNum){
        PageHelper.startPage(pageNum,10);
        List<CompanyVo> companyVos=tbCompanyService.selectAll();
        if (!companyVos.isEmpty()){
            return ajaxResult.ok(companyVos);
        }
        return ajaxResult.error("操作失败");
    }

    @ApiOperation("搜索公司")
    @GetMapping("/search")
    public AjaxResult searchCompany(
            @RequestParam @ApiParam("页数")int pageNum,
            @RequestParam @ApiParam("公司名称")String companyName){
        PageHelper.startPage(pageNum,10);
        List<CompanyVo> companyVos=tbCompanyService.selectByName(companyName);
        if (!companyVos.isEmpty()){
            return ajaxResult.ok(companyVos);
        }
        return ajaxResult.ok("没有此公司");
    }
////未完成
//    @ApiOperation("新增公司")
//    @GetMapping("/addCompany")
//    public AjaxResult addCompany(
//            @RequestParam(value = "file") @ApiParam("上传图片") MultipartFile upfile,
//            @RequestParam @ApiParam("公司名称")String companyName) throws IOException {
//        File file = new File(url + upfile.getOriginalFilename());
//        //将MulitpartFile文件转化为file文件格式
//        upfile.transferTo(file);
//        CompanyVo companyVo=new CompanyVo();
//        companyVo.setCompanyLogo(Path + "/" + fileService.uploadFile(file).get("imgName"));
//        companyVo.setCompanyName(companyName);
//        Integer result=tbCompanyService.addCompany(companyVo);
//        if (result!=0){
//            ajaxResult.ok("新增成功");
//        }
//        return new AjaxResult().error("新增失败");
//    }
//
//    @ApiOperation("编辑公司")
//    @GetMapping("/updateCompany")
//    public AjaxResult updateCompany(
//            @RequestParam @ApiParam("公司Id")int companyId,
//            @RequestParam @ApiParam("公司名称")String companyName,
//            @RequestParam(value = "file") @ApiParam("上传图片") MultipartFile upfile) throws IOException {
//        File file = new File(url + upfile.getOriginalFilename());
//        //将MulitpartFile文件转化为file文件格式
//        upfile.transferTo(file);
//        CompanyVo companyVo=new CompanyVo();
//        companyVo.setCompanyLogo(Path + "/" + fileService.uploadFile(file).get("imgName"));
//        companyVo.setCompanyName(companyName);
//        companyVo.setCompanyId(companyId);
////        Integer result=tbCompanyService.addCompany(companyVo);
////        if (result!=0){
////            ajaxResult.ok("编辑成功");
////        }
//        return new AjaxResult().error("编辑失败");
//    }
}

package com.zczp.controller_yycoder;

import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.service_yycoder.LabelService;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "前台管理--标签管理")
@RequestMapping("label")
public class LabelController {

    @Autowired(required = false)
    private LabelService labelService;

    @PostMapping("/city/add")
    @ApiOperation("新增城市标签")
    public AjaxResult addCityLabel(@RequestParam @ApiParam("城市名称") String cityName){
        int res = labelService.addCityLabel(cityName);
        if(res>0){
            return new AjaxResult().ok("新增城市标签成功");
        }
        return new AjaxResult().error("新增城市标签失败");
    }

    @PutMapping("/city/update")
    @ApiOperation("编辑城市标签")
    public AjaxResult updateCityLabel(
            @RequestParam @ApiParam("城市Id") Integer  cityId,
            @RequestParam @ApiParam("城市名称") String cityName) {
        TbCity tbCity =new TbCity();
        tbCity.setCityId(cityId);
        tbCity.setCityName(cityName);
        int res = labelService.updateCityLabel(tbCity);
        if(res>0){
            return new AjaxResult().ok("修改城市标签成功");
        }
        return new AjaxResult().error("修改城市标签失败");

    }

    @DeleteMapping("/city/delete")
    @ApiOperation("删除城市标签")
    public AjaxResult deleteCityLabel(@RequestParam @ApiParam("城市ID") Integer cityId){
        int res = labelService.deleteCityLabel(cityId);
        if(res>0){
            return new AjaxResult().ok("删除城市标签成功");
        }
        return new AjaxResult().error("删除城市标签失败");

    }

    @PostMapping("/postType/add")
    @ApiOperation("新增岗位标签")
    public AjaxResult addPostTypeLabel(@RequestParam @ApiParam("岗位名称") String typeName){
        int res = labelService.addPostTypeLabel(typeName);
        if(res>0){
            return new AjaxResult().ok("新增岗位标签成功");
        }
        return new AjaxResult().error("新增岗位标签失败");
    }

    @PutMapping("/postType/update")
    @ApiOperation("编辑岗位标签")
    public AjaxResult updatePostTypeLabel(
            @RequestParam @ApiParam("岗位Id") Integer  typeId,
            @RequestParam @ApiParam("岗位名称") String typeName) {
        TbPostType tbPostType=new TbPostType();
        tbPostType.setTypeId(typeId);
        tbPostType.setTypeName(typeName);
        int res = labelService.updatePostTypeLabel(tbPostType);
        if(res>0){
            return new AjaxResult().ok("修改岗位标签成功");
        }
        return new AjaxResult().error("修改岗位标签失败");

    }

    @DeleteMapping ("/postType/delete")
    @ApiOperation("删除岗位标签")
    public AjaxResult deletePostTypeLabel(@RequestParam @ApiParam("岗位ID") Integer typeId){

        int res = labelService.deletePostTypeLabel(typeId);
        if(res>0){
            return new AjaxResult().ok("删除岗位标签成功");
        }
        return new AjaxResult().error("删除岗位标签失败");
    }

}

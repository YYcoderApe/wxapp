package com.zczp.cmsController_yycoder;

import com.zczp.entity.TbPoster;
import com.zczp.service_yycoder.FileService;
import com.zczp.service_yycoder.PosterService;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "前台管理--banner管理")
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private PosterService posterService;

    @Autowired
    private FileService fileService;

    @GetMapping("/getAllBanner")
    @ApiOperation("获取所有海报")
    public AjaxResult getAllposter() {
        List<TbPoster> userList = posterService.getAllPoster();
        if (userList != null) {
            return new AjaxResult().ok(userList);
        }
        return new AjaxResult().error("库存中没有海报图");
    }

    @PostMapping("/addBanner")
    @ApiOperation("新增海报")
    public AjaxResult addPoster(
            @RequestParam @ApiParam("图片url") String posterImage,
            @RequestParam @ApiParam("海报链接url") String posterAddress) throws IOException {
        TbPoster tbPoster = new TbPoster();
        tbPoster.setPosterAddress(posterAddress);
        tbPoster.setPosterImage(posterImage);
        Map<String,Object> map = new HashMap<>();
        map.put("图片url",tbPoster.getPosterImage());
        map.put("posterUrl",tbPoster.getPosterAddress());
        map.put("state","SUCESS,海报信息新增成功");
        int result = posterService.addPoster(tbPoster);
        if (result > 0) {
            return new AjaxResult().ok(map);
        }
        return new AjaxResult().error("新增失败");
    }

    @PutMapping("/updateBanner")
    @ApiOperation("编辑海报")
    public AjaxResult updatePoster(
            @ModelAttribute TbPoster tbPoster) throws IOException{
        Map<String,Object> map = new HashMap<>();
        map.put("图片url",tbPoster.getPosterImage());
        map.put("posterUrl",tbPoster.getPosterAddress());
        map.put("state","SUCESS,海报图信息修改成功");
        int i = posterService.updatePoster(tbPoster);
        if (i > 0) {
            return new AjaxResult().ok(map);
        }
        return new AjaxResult().error("海报图修改信息失败");
    }

    @DeleteMapping("/deleteBanner")
    @ApiOperation("删除海报")
    public AjaxResult deletePoster(@RequestParam("poster_id") Integer poster_id) {
        int i = posterService.deletePoster(poster_id);
        if (i > 0) {
            return new AjaxResult().ok("海报图信息删除成功");
        }
        return new AjaxResult().error("海报图信息删除失败");
    }
}

package com.zczp.controller_yycoder;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPoster;
import com.zczp.service_yycoder.FileService;
import com.zczp.service_yycoder.PosterService;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "海报轮播图")
@RequestMapping("poster")
public class PosterController {
    @Value("${baseUploadUrl}")
    private String url;

    @Value("${qiniu.path}")
    private String Path;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PosterService posterService;
    @Autowired
    private FileService fileService;

    @GetMapping("/getAllPoster")
    @ApiOperation("获取所有海报轮播图")
    public AjaxResult getAllposter() {
        List<TbPoster> userList = posterService.getAllPoster();
        if (userList != null) {
            return new AjaxResult().ok(userList);
        }
        return new AjaxResult().error("库存中没有海报图");
    }

    @GetMapping("/getPoster")
    @ApiOperation("获取poster_id对应海报轮播图")
    public AjaxResult getPoster(@RequestParam("poster_id") Integer poster_id) {
        TbPoster tbPoster = posterService.getPosterById(poster_id);
        if (tbPoster != null) {
            return new AjaxResult().ok(tbPoster);
        }
        return new AjaxResult().error("你所浏览的海报图不存在");
    }

    @PostMapping("/addPoster")
    @ApiOperation("后台-增加海报图banneer")
    public AjaxResult addPoster(
            @RequestParam(value = "file") @ApiParam("上传图片") MultipartFile upfile,
            @RequestParam @ApiParam("海报图路径") String posterUrl) throws IOException {
        TbPoster tbPoster = new TbPoster();
        File file = new File(url + upfile.getOriginalFilename());
        //将MulitpartFile文件转化为file文件格式
        upfile.transferTo(file);
        //访问的具体网址
        tbPoster.setPosterImage(Path + "/" + fileService.uploadFile(file).get("imgName"));
        tbPoster.setPosterAddress(posterUrl);
        int result = posterService.addPoster(tbPoster);
        if (result > 0) {
            return new AjaxResult().ok(tbPoster + "/n" + "新增成功");
        }
        return new AjaxResult().error("新增失败");
    }

    @PutMapping("/updatePoster")
    @ApiOperation("后台-编辑海报图banneer")
    public AjaxResult updatePoster(
            @RequestParam @ApiParam("海报图posterId") Integer posterId,
            @RequestParam(value = "file") @ApiParam("上传图片") MultipartFile upfile,
            @RequestParam @ApiParam("海报图路径") String posterUrl) throws IOException{
        TbPoster tbPoster = new TbPoster();
        File file = new File(url + upfile.getOriginalFilename());
        //将MulitpartFile文件转化为file文件格式
        upfile.transferTo(file);
        //访问的具体网址
        tbPoster.setPosterId(posterId);
        tbPoster.setPosterImage(Path + "/" + fileService.uploadFile(file).get("imgName"));
        tbPoster.setPosterAddress(posterUrl);
        int i = posterService.updatePoster(tbPoster);
        if (i > 0) {
            return new AjaxResult().ok("海报图信息修改成功");
        }
        return new AjaxResult().error("海报图修改信息失败");
    }

    @DeleteMapping("/deletePoster")
    @ApiOperation("后台-删除海报图banneer")
    public AjaxResult deletePoster(@RequestParam("poster_id") Integer poster_id) {
        int i = posterService.deletePoster(poster_id);
        if (i > 0) {
            return new AjaxResult().ok("海报图信息删除成功");
        }
        return new AjaxResult().error("海报图信息删除失败");
    }
}

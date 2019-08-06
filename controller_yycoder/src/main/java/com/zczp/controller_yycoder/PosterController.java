package com.zczp.controller_yycoder;

import com.zczp.entity.TbPoster;
import com.zczp.service_yycoder.PosterService;
import com.zczp.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "海报轮播图")
@RequestMapping("poster")
public class PosterController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PosterService posterService;

    @GetMapping("/getAllPoster")
    @ApiOperation("获取所有海报轮播图")
    public AjaxResult getAllposter(){
        List<TbPoster> userList =posterService.getAllPoster();
        if(userList!=null){
            return new AjaxResult().ok(userList);
        }
        return new AjaxResult().error("库存中没有海报图");
    }

    @GetMapping("/getPoster")
    @ApiOperation("获取poster_id对应海报轮播图")
    public AjaxResult getPoster(@RequestParam("poster_id") Integer poster_id){
        TbPoster tbPoster=posterService.getPosterById(poster_id);
        if(tbPoster!=null){
            return new AjaxResult().ok(tbPoster);
        }
        return new AjaxResult().error("你所浏览的海报图不存在");
    }
}

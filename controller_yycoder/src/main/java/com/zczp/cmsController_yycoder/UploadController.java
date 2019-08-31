package com.zczp.cmsController_yycoder;


import com.zczp.service_yycoder.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：
 * @ClassName ：UploadController
 * @date : 2019/8/2 17:47
 * @description : TODO
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "图片上传接口")
public class UploadController {

    @Autowired
    FileService fileService;

    @Value("${qiniu.path}")
    private String Path;

    @PostMapping(value = "/uploadImg")
    @ApiOperation(value = "单个图片上传到七牛云")
    public Map<String, Object> uploadImg(@RequestParam(value = "file") MultipartFile upfile) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String fileName = upfile.getOriginalFilename();
        File file = new File("/data/tmp/" + fileName);
        //将MulitpartFile文件转化为file文件格式
        upfile.transferTo(file);
        Map response = fileService.uploadFile(file);
        Object imageName = response.get("imgName");
        map.put("state", "SUCESS");
        //访问的具体网址
        map.put("return_url", Path + "/" + imageName);
        return map;
    }
}


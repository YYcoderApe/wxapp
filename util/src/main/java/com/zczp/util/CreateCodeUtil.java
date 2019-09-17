package com.zczp.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 *@author: cancer
 *@data: 2019/9/12 20:22
 *@descriptions: 生成小程序码工具类
 *@version: 1.0
 */
@Component
public class CreateCodeUtil {
    private final Logger logger= LoggerFactory.getLogger(CreateCodeUtil.class);

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String Path;

    private StringMap putPolicy;

    public Map CreateCode(String page){
        //请求参数
        Map<String,String> param = new HashMap<>();
        param.put("grant_type","client_credential");
        param.put("appid",appId);
        param.put("secret",appSecret);
        //发送获取token请求
        String tokenResult=HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/token",param);
        Map<String,Object> jsonObject =JsonUtils.json2object(tokenResult, Map.class, String.class, Object.class);
        String token=jsonObject.get("access_token").toString();
        logger.info("===获取token===>"+token);
        //请求参数
        JsonObject json=new JsonObject();
        json.addProperty("width",430);
        json.addProperty("scene","a=3");
        if (!page.isEmpty()){
            json.addProperty("page",page);
        }
        // 创建Httpclient对象
        String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream is=null;
        Map map = new HashMap();
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
            entity.setContentType("image/jpg");
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            is=response.getEntity().getContent();
            //将buffer转换位字节数组
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bs = new byte[1024];//缓冲数组
            int len = -1;
            while ((len = is.read(bs)) != -1) {
                byteArrayOutputStream.write(bs, 0, len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            is.close();
            //上传字节数组至七牛云
            String uploadToken =this.auth.uploadToken(bucket,null,3600,putPolicy);
            Response response1 = this.uploadManager.put(bytes,null,uploadToken);
            //解析上传的结果
            DefaultPutRet putRet = new Gson().fromJson(response1.bodyString(),DefaultPutRet.class);
            String imageName = putRet.hash;
            int retry = 0;
            while(response1.needRetry() && retry < 3){
                response1 = this.uploadManager.put(bytes,null,uploadToken);
            }
            map.put("response",response1);
            map.put("imgName",imageName);
            map.put("return_url", Path + "/" + imageName);
            logger.info("===上传返回信息===>"+map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}

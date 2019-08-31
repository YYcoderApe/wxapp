package com.zczp.util;

import com.zczp.entity.WxAccount;
import com.zczp.vo_cancer.AuthorizeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Component
public class WeChatUtil {
    private final Logger logger = LoggerFactory.getLogger(WeChatUtil.class);

    @Value("${wx.url}")
    private String url;

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    /**
     * wx.login授权获得openid和session_key
     */
    public WxAccount login(String code) {
        //请求参数
        Map<String,String> param=new HashMap<>();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        //发送请求
        String wxResult = HttpClientUtil.doGet(url,param);
//        JsonObject jsonObject = new JsonParser().parse(wxResult).getAsJsonObject();
        Map<String, Object> jsonObject = JsonUtils.json2object(wxResult, Map.class, String.class, Object.class);
        String openId =jsonObject.get("openid").toString();
        String sessionKey = jsonObject.get("session_key").toString();
        WxAccount wxAccount=new WxAccount(openId,sessionKey);
        return wxAccount;
    }

    /**
     * wx.authorize授权获得openid和session_key
     *
     * @param authorizeVO
     * @return userInfo
     */
    @Transactional
    public WxAccount authorize(AuthorizeVO authorizeVO) {
        //请求参数
        Map<String,String> param=new HashMap<>();
        param.put("appid", appId);
        param.put("secret", appSecret);
        param.put("js_code", authorizeVO.getCode());
        param.put("grant_type", "authorization_code");
        //发送请求
        String wxResult = HttpClientUtil.doGet(url,param);
//        JsonObject jsonObject = new JsonParser().parse(wxResult).getAsJsonObject();
        Map<String, Object> jsonObject = JsonUtils.json2object(wxResult, Map.class, String.class, Object.class);
        if (jsonObject==null){
            logger.info("获取openID和sessionKey失败");
            return null;
        }
        logger.info("获取Openid："+jsonObject.get("openid").toString()+"获取sessionKey:" +jsonObject.get("session_key"));
        String openId =jsonObject.get("openid").toString();
        String sessionKey = jsonObject.get("session_key").toString();
        WxAccount wxAccount=new WxAccount(openId,sessionKey);
        return wxAccount;
    }

        /**
     * AES解密
     *
     * @param data 密文
     * @param key  秘钥
     * @param iv   偏移量
     * @return
     */
//    private String decrypt(String data, String key, String iv) {
//        //待加密数据,加密秘钥,偏移量
//        byte[] dataByte = Base64Utils.decode(data.getBytes()), keyByte = Base64Utils.decode(key.getBytes()),
//                ivByte = Base64Utils.decode(iv.getBytes());
//        try {
////            Cipher.getInstance("AES/DES/CBC/PKCS5Padding");
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//            parameters.init(new IvParameterSpec(ivByte));
//            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
//            byte[] resultByte = cipher.doFinal(dataByte);
//
//            if (null != resultByte && resultByte.length > 0) {
//                String result = new String(resultByte, "UTF-8");
//                logger.error(" result is {} ", result);
//                return result;
//            }
//        } catch (Exception e) {
//            logger.error(" decrypt error {} ", e);
//        }
//        return null;
//    }

}

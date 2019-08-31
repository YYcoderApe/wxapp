package com.zczp.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
/**
 *@author: cancer
 *@data: 2019/8/9 17:43
 *@descriptions: token功能类
 *@version: 1.0
 */
@Component
public class TokenUtil {
    @Autowired
    HttpServletRequest request;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    JwtUtil jwtUtil;

    public String getOpenId(String token){
        /**
         * @method  getOpenId
         * @description 获取openId
         * @date: 2019/8/9 17:49
         * @author: Cancer
         * @param [result]
         * @return java.lang.String
         */
        boolean result=jwtUtil.verifyToken(token);
        if (!result){
            return null;
        }
        String openId=jwtUtil.getWxOpenIdByToken(token);
        return openId;
    }
//    public String getOpenId(String result){
//        /**
//         * @method  getOpenId
//         * @description 获取openId
//         * @date: 2019/8/9 17:49
//         * @author: Cancer
//         * @param [result]
//         * @return java.lang.String
//         */
//        String token=request.getHeader(result);
//        String openId;
//        if (jwtUtil.verifyToken(token)) {
//            openId = jwtUtil.getWxOpenIdByToken(token);
//        }else {
//            openId=null;
//        }
//        return openId;
//    }

}

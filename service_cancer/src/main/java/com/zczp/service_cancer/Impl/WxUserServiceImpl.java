package com.zczp.service_cancer.Impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zczp.dao.TbUserMapper;
import com.zczp.entity.TbUser;
import com.zczp.entity.WxAccount;
import com.zczp.service_cancer.WxUserService;
import com.zczp.util.AesCbcUtil;
import com.zczp.util.JwtUtil;
import com.zczp.util.WeChatUtil;
import com.zczp.vo_cancer.AuthorizeVO;
import com.zczp.vo_cancer.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl implements WxUserService {
    private final Logger logger = LoggerFactory.getLogger(WxUserServiceImpl.class);
    @Autowired
    private WeChatUtil weChatUtil;
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String authorize(AuthorizeVO authorizeVO) {
        WxAccount wxAccount=weChatUtil.authorize(authorizeVO);
        //解密用户信息
        String result =AesCbcUtil.decrypt(authorizeVO.getData(),wxAccount.getSessionKey(),authorizeVO.getIv(),"UTF-8");
        JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
        TbUser tbUser =new TbUser();
        if (result!=null) {
            tbUser.setOpenId(wxAccount.getWxOpenid());
            tbUser.setUserName(jsonObject.get("nickName").toString());
            tbUser.setUserGender(jsonObject.get("gender").toString());
            tbUser.setCity(jsonObject.get("city").toString());
            tbUser.setProvince(jsonObject.get("province").toString());
            tbUser.setCountry(jsonObject.get("country").toString());
            tbUser.setUserImage(jsonObject.get("avatarUrl").toString());
            tbUser.setLanguage(jsonObject.get("language").toString());
        }
        Integer result1=tbUserMapper.insert(tbUser);
        if (result1!=0){
            logger.info("tbUser is{}",tbUser.toString());
        }else {
            logger.info("授权存入tbUser失败");
        }
        return jwtUtil.createTokenByWxAccount(wxAccount);
    }

    @Override
    public String login(String code) {
        WxAccount wxAccount=weChatUtil.login(code);
        return jwtUtil.createTokenByWxAccount(wxAccount);
    }
}

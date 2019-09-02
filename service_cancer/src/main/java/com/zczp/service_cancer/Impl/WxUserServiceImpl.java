package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbUserMapper;
import com.zczp.entity.TbUser;
import com.zczp.entity.WxAccount;
import com.zczp.service_cancer.WxUserService;
import com.zczp.util.AesCbcUtil;
import com.zczp.util.JsonUtils;
import com.zczp.util.JwtUtil;
import com.zczp.util.WeChatUtil;
import com.zczp.vo_cancer.AuthorizeVO;
import com.zczp.vo_yycoder.UserDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        String result =AesCbcUtil.decrypt(authorizeVO.getData(),"\""+wxAccount.getSessionKey()+"\"",authorizeVO.getIv(),"UTF-8");
        TbUser tbUser =new TbUser();
        //工具类转换
        if (result!=null) {
            Map<String, Object> userInfoMap = JsonUtils.json2object(result, Map.class, String.class, Object.class);
            tbUser.setOpenId(userInfoMap.get("openId").toString());
            tbUser.setUserName(userInfoMap.get("nickName").toString());
            if (userInfoMap.get("gender").toString()=="1"){
                tbUser.setUserGender("男");
            }else{
                tbUser.setUserGender("女");
            }
            tbUser.setCity(userInfoMap.get("city").toString());
            tbUser.setProvince(userInfoMap.get("province").toString());
            tbUser.setCountry(userInfoMap.get("country").toString());
            tbUser.setUserImage(userInfoMap.get("avatarUrl").toString());
            tbUser.setLanguage(userInfoMap.get("language").toString());
            tbUser.setState(0);
        }
        UserDetailVo userDetailVo=tbUserMapper.getUserByOpenId(wxAccount.getWxOpenid());
        if (userDetailVo!=null)return "用户已存在";
        Integer result1=tbUserMapper.insert(tbUser);
        if (result1!=0){
            logger.info("授权保存tbUser is{}",tbUser.toString());
        }else {
            logger.info("授权存入tbUser失败");
        }
        return jwtUtil.createTokenByWxAccount(wxAccount);
    }

    @Override
    public String login(String code) {
        WxAccount wxAccount=weChatUtil.login(code);
        logger.info("登陆操作  "+"OPENID:"+wxAccount.getWxOpenid()+"SESSION:"+wxAccount.getSessionKey());
        return jwtUtil.createTokenByWxAccount(wxAccount);
    }

    @Override
    public int save(TbUser tbUser) {
        return tbUserMapper.insert(tbUser);
    }
}

package com.zczp.dao;

import com.zczp.entity.TbUser;
import com.zczp.vo_yycoder.UserDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserMapper extends BaseMapper<TbUser>{

    //根据登录传来的openid展示用户信息
    List<UserDetailVo> getUserByOpenId(String openId);

    //修改用户信息
    int updateUserInfoById( UserDetailVo userDetailVo);

    //查看个人收藏
    List<Integer> getUserCollection( String openId);

}
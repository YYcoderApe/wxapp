package com.zczp.dao;

import com.zczp.entity.TbUser;
import com.zczp.vo_yycoder.UserDetailVo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbUserMapper extends BaseMapper<TbUser>{

    //根据登录传来的openid展示用户信息
    List<UserDetailVo> getUserById(Integer id);
}
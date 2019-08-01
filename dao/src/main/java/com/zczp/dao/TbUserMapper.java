package com.zczp.dao;

import com.zczp.entity.TbUser;

public interface TbUserMapper {
    int deleteByPrimaryKey(String openId);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}
package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbPostType;

public interface TbPostTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TbPostType record);

    int insertSelective(TbPostType record);

    TbPostType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TbPostType record);

    int updateByPrimaryKey(TbPostType record);
}
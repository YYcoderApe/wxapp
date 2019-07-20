package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbPost;
import com.example.wxappdemo.entity.TbPostWithBLOBs;

public interface TbPostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(TbPostWithBLOBs record);

    int insertSelective(TbPostWithBLOBs record);

    TbPostWithBLOBs selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);
}
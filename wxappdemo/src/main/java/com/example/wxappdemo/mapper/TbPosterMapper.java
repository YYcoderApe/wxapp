package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbPoster;

public interface TbPosterMapper {
    int deleteByPrimaryKey(Integer posterId);

    int insert(TbPoster record);

    int insertSelective(TbPoster record);

    TbPoster selectByPrimaryKey(Integer posterId);

    int updateByPrimaryKeySelective(TbPoster record);

    int updateByPrimaryKey(TbPoster record);
}
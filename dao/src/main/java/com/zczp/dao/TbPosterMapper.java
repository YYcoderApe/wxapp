package com.zczp.dao;

import com.zczp.entity.TbPoster;

import java.util.List;

public interface TbPosterMapper {
    int deleteByPrimaryKey(Integer posterId);

    int insert(TbPoster record);

    int insertSelective(TbPoster record);

    TbPoster selectByPrimaryKey(Integer posterId);

    int updateByPrimaryKeySelective(TbPoster record);

    int updateByPrimaryKey(TbPoster record);

    //查询所有海报轮播图
    List<TbPoster> selectAllPoster();
}
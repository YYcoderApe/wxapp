package com.zczp.dao;

import com.zczp.entity.TbPoster;

public interface TbPosterMapper {
    int deleteByPrimaryKey(Integer posterId);

    int insert(TbPoster record);

    int insertSelective(TbPoster record);

    TbPoster selectByPrimaryKey(Integer posterId);

    int updateByPrimaryKeySelective(TbPoster record);

    int updateByPrimaryKey(TbPoster record);
}
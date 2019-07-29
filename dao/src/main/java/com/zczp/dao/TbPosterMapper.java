package com.zczp.dao;

import com.zczp.entity.TbPoster;

import java.util.List;

public interface TbPosterMapper extends BaseMapper<TbPoster>{

    //查询所有海报轮播图
    List<TbPoster> selectAllPoster();
}
package com.zczp.dao;

import com.zczp.entity.TbPoster;

import java.util.List;

public interface TbPosterMapper extends BaseMapper<TbPoster>{

    //查询所有海报轮播图
    List<TbPoster> selectAllPoster();

    //重新修改序号顺序
    int updateSerialNumber(Integer poster_id);
}
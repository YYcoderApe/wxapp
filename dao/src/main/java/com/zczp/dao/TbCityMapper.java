package com.zczp.dao;

import com.zczp.entity.TbCity;

import java.util.List;

public interface TbCityMapper extends BaseMapper<TbCity>{

    //cancer的
    List<TbCity> selectAll();

    //查看所有城市的分类
    List<TbCity> selectAllCity();
}
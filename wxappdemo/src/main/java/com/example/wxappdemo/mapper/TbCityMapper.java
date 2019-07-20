package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbCity;

public interface TbCityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);
}
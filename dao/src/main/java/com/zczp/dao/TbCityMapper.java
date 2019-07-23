package com.zczp.dao;

import com.zczp.entity.TbCity;

public interface TbCityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);
}
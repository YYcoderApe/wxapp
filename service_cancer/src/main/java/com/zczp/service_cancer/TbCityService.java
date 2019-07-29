package com.zczp.service_cancer;

import com.zczp.entity.TbCity;

import java.util.List;

public interface TbCityService {
    int deleteByPrimaryKey(Integer cityId);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);

    List<TbCity> selectAll();
}

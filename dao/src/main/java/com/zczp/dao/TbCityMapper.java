package com.zczp.dao;

import com.zczp.entity.TbCity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbCityMapper extends BaseMapper<TbCity>{
    int deleteByPrimaryKey(Integer cityId);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    TbCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);

    List<TbCity> selectAll();
}
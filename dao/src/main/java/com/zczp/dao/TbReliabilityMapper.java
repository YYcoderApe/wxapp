package com.zczp.dao;

import com.zczp.entity.TbReliability;

public interface TbReliabilityMapper {
    int deleteByPrimaryKey(Integer reliabilityId);

    int insert(TbReliability record);

    int insertSelective(TbReliability record);

    TbReliability selectByPrimaryKey(Integer reliabilityId);

    int updateByPrimaryKeySelective(TbReliability record);

    int updateByPrimaryKey(TbReliability record);
}
package com.zczp.dao;

import com.zczp.entity.TbCollect;

public interface TbCollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    TbCollect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);
}
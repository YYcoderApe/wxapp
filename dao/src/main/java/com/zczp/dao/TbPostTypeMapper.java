package com.zczp.dao;

import com.zczp.entity.TbPostType;

public interface TbPostTypeMapper extends BaseMapper<TbPostType> {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TbPostType record);

    int insertSelective(TbPostType record);

    TbPostType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TbPostType record);

    int updateByPrimaryKey(TbPostType record);
}
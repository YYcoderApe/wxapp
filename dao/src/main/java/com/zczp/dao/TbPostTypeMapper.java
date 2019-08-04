package com.zczp.dao;

import com.zczp.entity.TbPostType;

import java.util.List;

public interface TbPostTypeMapper extends BaseMapper<TbPostType> {
    //查询所有岗位类型的分类
    List<TbPostType> getAllJobType();
}
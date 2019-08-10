package com.zczp.dao;

import com.zczp.entity.TbPostType;

import java.util.List;

public interface TbPostTypeMapper extends BaseMapper<TbPostType> {
    //查询所有岗位类型的分类
    List<TbPostType> getAllJobType();

    //序号重新排序
    int updateSerialNumber(Integer typeId);

    //获取数据库中id的最大值
    int getMaxTypeId();
}
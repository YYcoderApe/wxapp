package com.zczp.dao;

import com.zczp.entity.TbPostType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbPostTypeMapper extends BaseMapper<TbPostType> {
    List<TbPostType> selectAll();

    //查询所有岗位类型的分类
    List<TbPostType> getAllJobType();
}
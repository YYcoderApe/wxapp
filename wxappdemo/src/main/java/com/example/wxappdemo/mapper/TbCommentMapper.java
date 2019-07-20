package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbComment;

public interface TbCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(TbComment record);

    int insertSelective(TbComment record);

    TbComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(TbComment record);

    int updateByPrimaryKey(TbComment record);
}
package com.example.wxappdemo.mapper;

import com.example.wxappdemo.entity.TbAskReply;

public interface TbAskReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAskReply record);

    int insertSelective(TbAskReply record);

    TbAskReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbAskReply record);

    int updateByPrimaryKey(TbAskReply record);
}
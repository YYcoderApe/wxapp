package com.zczp.dao;

import com.zczp.entity.TbAskReply;

public interface TbAskReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbAskReply record);

    int insertSelective(TbAskReply record);

    TbAskReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbAskReply record);

    int updateByPrimaryKey(TbAskReply record);
}
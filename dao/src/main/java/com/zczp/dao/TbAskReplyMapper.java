package com.zczp.dao;

import com.zczp.entity.TbAskReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAskReplyMapper extends BaseMapper<TbAskReply> {
        //通过open_id查询问答表
        List<TbAskReply> getAskReplyByOpenId(@Param("openId") String openId);
}
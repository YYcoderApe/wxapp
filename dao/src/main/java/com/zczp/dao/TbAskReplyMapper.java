package com.zczp.dao;

import com.zczp.entity.TbAskReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAskReplyMapper extends BaseMapper<TbAskReply> {
        Integer selectByPostIdAndOpenId(
                @Param("postId")Integer postId,
                @Param("openId") String openId);

        //通过open_id查询问答表
        List<TbAskReply> getAskReplyByOpenId(@Param("openId") String openId);

        //通过Id删除
        int deleteById(
                @Param("openId") String openId,
                @Param("postId") Integer postId);
}
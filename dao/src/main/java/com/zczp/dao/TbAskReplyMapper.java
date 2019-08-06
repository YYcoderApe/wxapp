package com.zczp.dao;

import com.zczp.entity.TbAskReply;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Repository
public interface TbAskReplyMapper extends BaseMapper<TbAskReply> {
    Integer selectByPostIdAndOpenId(Integer postId,String openId);

        //通过open_id查询问答表
        List<TbAskReply> getAskReplyByOpenId(@Param("openId") String openId);
}
package com.zczp.dao;

import com.zczp.entity.TbAskReply;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAskReplyMapper extends BaseMapper<TbAskReply> {
    Integer selectByPostIdAndOpenId(Integer postId,String openId);

}
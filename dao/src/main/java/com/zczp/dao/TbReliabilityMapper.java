package com.zczp.dao;

import com.zczp.entity.TbReliability;
import org.springframework.stereotype.Repository;

@Repository
public interface TbReliabilityMapper extends BaseMapper<TbReliability>{

    int updateByUserIdAndPostId(TbReliability record);

    Integer selectByPostIdAndUserId(int postId, String openId);
}
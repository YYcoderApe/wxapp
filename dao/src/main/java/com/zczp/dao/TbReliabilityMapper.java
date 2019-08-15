package com.zczp.dao;

import com.zczp.entity.TbReliability;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbReliabilityMapper extends BaseMapper<TbReliability>{

    int updateByUserIdAndPostId(TbReliability record);

    Integer selectByPostIdAndUserId(
            @Param("postId") Integer postId,
            @Param("openId") String openId);
}
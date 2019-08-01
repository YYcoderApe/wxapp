package com.zczp.dao;

import com.zczp.entity.TbReliability;
import org.springframework.stereotype.Repository;

@Repository
public interface TbReliabilityMapper {
    int deleteByPrimaryKey(Integer reliabilityId);

    int insert(TbReliability record);

    int insertSelective(TbReliability record);

    TbReliability selectByPrimaryKey(Integer reliabilityId);

    int updateByPrimaryKeySelective(TbReliability record);

    int updateByPrimaryKey(TbReliability record);

    int updateByUserIdAndPostId(TbReliability record);

    Integer selectByPostIdAndUserId(int postId, String openId);
}
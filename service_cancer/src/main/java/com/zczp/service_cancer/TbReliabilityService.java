package com.zczp.service_cancer;

import com.zczp.entity.TbReliability;

public interface TbReliabilityService {
    int deleteByPrimaryKey(Integer reliabilityId);

    int insert(TbReliability record);

    int insertSelective(TbReliability record);

    TbReliability selectByPrimaryKey(Integer reliabilityId);

    int updateByPrimaryKeySelective(TbReliability record);

    int updateByPrimaryKey(TbReliability record);

    Integer selectByPostIdAndUserId(int postId, String openId);

    void saveReliabilityState(String openId,int postId);

    void delReliabilityState(String openId,int postId);

    void transReliabilityToDB();

}

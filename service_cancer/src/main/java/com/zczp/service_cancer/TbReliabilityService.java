package com.zczp.service_cancer;

import com.zczp.entity.TbReliability;

public interface TbReliabilityService {

    Integer selectByPostIdAndUserId(int postId, String openId);

    void saveReliabilityState(String openId,int postId);

    void delReliabilityState(String openId,int postId);

    void transReliabilityToDB();

}

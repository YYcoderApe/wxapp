package com.zczp.service_cancer;

import com.zczp.entity.TbCollect;

public interface TbCollectService {
    int deleteByPrimaryKey(Integer collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    TbCollect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);

    Integer selectByPostIdAndUserId(Integer postId, String openId);
}

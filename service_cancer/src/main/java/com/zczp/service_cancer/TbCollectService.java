package com.zczp.service_cancer;

import com.zczp.entity.TbCollect;

public interface TbCollectService {
    int deleteByPrimaryKey(Integer collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    Integer selectByPostIdAndUserId(Integer postId, String openId);

    void saveCollectState(int postId, String openId);

    void delCollectState(int postId, String openId);

    void transCollectToDB();
}

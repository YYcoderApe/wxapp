package com.zczp.dao;

import com.zczp.entity.TbCollect;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCollectMapper extends BaseMapper<TbCollect>{
    int deleteByPrimaryKey(Integer collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    TbCollect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);

    Integer selectByPostIdAndUserId(Integer postId, String openId);

    int updateByPostIdAndOpenId(TbCollect tbCollect);
}
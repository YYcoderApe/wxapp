package com.zczp.dao;

import com.zczp.entity.TbCollect;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbCollectMapper extends BaseMapper<TbCollect>{

    //根据postId进行查找对应的post信息(多表关联)
    List<CollectPostDetailVo> getPostDetailById(String openId);

    //用户删除自己收藏的post招聘表
    int deleteCollectionById(
            @Param("openId") String openId,
            @Param("postId") Integer postId);

    Integer selectByPostIdAndUserId(Integer postId, String openId);
}
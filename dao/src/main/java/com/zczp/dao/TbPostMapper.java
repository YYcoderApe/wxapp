package com.zczp.dao;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_cancer.PostDetailVo;
import org.springframework.stereotype.Repository;

@Repository
public interface TbPostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(TbPostWithBLOBs record);

    int insertSelective(TbPostWithBLOBs record);

    TbPostWithBLOBs selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);

    PostDetailVo selectDetailByPrimaryKey(Integer postId);
}
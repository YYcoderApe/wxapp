package com.zczp.service_cancer;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_cancer.PostDetailVo;

public interface TbPostService {
    int deleteByPrimaryKey(Integer postId);

    int insert(TbPostWithBLOBs record);

    int insertSelective(TbPostWithBLOBs record);

    TbPostWithBLOBs selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);

    PostDetailVo selectDetailByPrimaryKey(Integer postId);
}

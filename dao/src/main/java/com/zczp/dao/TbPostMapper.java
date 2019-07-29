package com.zczp.dao;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface TbPostMapper extends BaseMapper<TbPostWithBLOBs>{
    int deleteByPrimaryKey(Integer postId);


    int insertSelective(TbPostWithBLOBs record);

    TbPostWithBLOBs selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(TbPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);

    PostDetailsVo selectDetailByPrimaryKey(Integer postId);

    //取出Post表中字段信息展现在首页
    List<PostDetailVo> getPostDetail();

    //根据条件展示招聘信息
    List<PostDetailVo> getPostByCityName(
            @Param("cityName") String cityName,
            @Param("jobType")String jobType,
            @Param("postType") String postType);

    List<TbPost> getAllPost();


}
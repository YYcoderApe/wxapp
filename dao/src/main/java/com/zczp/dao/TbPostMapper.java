package com.zczp.dao;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_yycoder.PostDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPostMapper  extends BaseMapper<TbPostWithBLOBs>{

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);
    //取出Post表中字段信息展现在首页
    List<PostDetailVo> getPostDetail();

    //根据条件展示招聘信息
    List<PostDetailVo> getPostByCityName(
            @Param("cityName") String cityName,
            @Param("jobType")String jobType,
            @Param("postType") String postType);

    List<TbPost> getAllPost();


}
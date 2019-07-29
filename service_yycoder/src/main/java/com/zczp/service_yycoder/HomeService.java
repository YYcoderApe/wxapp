package com.zczp.service_yycoder;


import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_yycoder.PostDetailVo;

import java.util.List;

public interface HomeService {

    //查询post中所需要的字段打包给前端
    List<PostDetailVo> getPostDetail();

    //查询post表对应的详情TbPostWithBLOBs
    TbPostWithBLOBs selectByPostId(Integer postId);

    //根据城市cityName 进行字段查询
    List<PostDetailVo> getPostByCityName(String cityName, String jobType, String postType);

//    //根据招聘类型jobType就行查询
//    List<PostDetailVo> getPostByJobType(String jobType);
//
//    //根据岗位类型typeId进行查询
//    List<PostDetailVo> getPostByPostType(String postType);

}

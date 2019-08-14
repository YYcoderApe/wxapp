package com.zczp.service_yycoder;


import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;


import java.util.List;

public interface HomeService {

    //查询post中所需要的字段打包给前端
    List<PostDetailVo> getPostDetail();

//    //查询post_id对应的postDetail信息
//    PostDetailVo getPostDetailById(Integer postId);

    //获取城市的全部分类
    List<TbCity> getAllCitySort();

    //获取职位类型分类
    List<TbPostType> getAllJobTypeSort();

    //根据城市cityName 进行字段查询
//    List<PostDetailVo> getPostByCondition(String cityName, String jobType, String postType);
    List<PostDetailVo> getPostByCondition(ConditionVo conditionVo);
}

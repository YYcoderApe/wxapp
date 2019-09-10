package com.zczp.dao;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;
import io.swagger.models.auth.In;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPostMapper  extends BaseMapper<TbPostWithBLOBs>{

    int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record);

    int updateByPrimaryKey(TbPost record);

    PostDetailsVo selectDetailByPrimaryKey(@Param("postId") Integer postId);

    List<PostDetailVo> selectByTitle(String title);

    List<TbPost> getAllPost();

    int updateReliabilityByPrimaryKey(TbPostWithBLOBs record);

    //取出对应用户openId发布的招聘信息
    List<PostDetailVo> getPostIssueByOpenId(String openId);

    //删除user发布post_id的对应的招聘信息
    int deleteUserIssueById(
            @Param("openId") String openId,
            @Param("postId") Integer postId);

    //取出Post表中字段信息展现在首页
    List<PostDetailVo> getPostDetail();

    //根据条件展示招聘信息;
    List<PostDetailVo> getPostByCondition(ConditionVo conditionVo);

    //根据post_id查找post信息
    CollectPostDetailVo getPostDetailById(@Param("postId") Integer postId);

    List<PostDetailVo> selectByCompanyAndState(String company);

    List<PostDetailVo> selectByCompany(String company);

    int getTotalTags();

    Integer updateStateByPostId(int postId,int state);
}
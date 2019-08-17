package com.zczp.service_cancer;

import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface TbPostService {

    int insert(TbPostWithBLOBs record);

    TbPostWithBLOBs selectByPrimaryKey(Integer postId);

    PostDetailsVo selectDetailByPrimaryKey(Integer postId,String openId,Integer pageNum);

    List<PostDetailVo> selectByTitle(String title);

    void transReliabilityCountToDB();

    List<PostDetailVo> selectByCompany(String company);

    int deletePostById(int postId);

    List<String> getSearchHistory();

    void deleteHistory();
}

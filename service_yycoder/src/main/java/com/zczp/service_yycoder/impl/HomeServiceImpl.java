package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_yycoder.HomeService;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    List<PostDetailVo> postDetailVoList;

    @Override
    public List<PostDetailVo> getPostDetail() {
        postDetailVoList= tbPostMapper.getPostDetail();
        return postDetailVoList;
    }

    @Override
    public TbPostWithBLOBs selectByPostId(Integer postId) {
        TbPostWithBLOBs tbPostWithBLOBs = tbPostMapper.selectByPrimaryKey(postId);
        if(tbPostWithBLOBs!=null){
            return tbPostWithBLOBs;
        }
        return null;
    }

    @Override
    public List<PostDetailVo> getPostByCityName(String cityName, String jobType, String postType) {
        postDetailVoList=tbPostMapper.getPostByCityName(cityName,jobType,postType);
        return postDetailVoList;

    }

}

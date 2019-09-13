package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.dao.TbPostTypeMapper;
import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.service_yycoder.HomeService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    @Autowired(required = false)
    private TbCityMapper tbCityMapper;

    @Autowired(required = false)
    private TbPostTypeMapper tbPostTypeMapper;

    @Autowired
    RedisUtil redisUtil;

    List<PostDetailVo> postDetailVoList=null;


    @Override
    public List<TbCity> getAllCitySort() {
        List<TbCity> tbCityList=tbCityMapper.selectAllCity();
        return tbCityList;
    }

    @Override
    public List<TbPostType> getAllJobTypeSort() {
        List<TbPostType> tbPostTypeMapperList= tbPostTypeMapper.getAllJobType();
        return tbPostTypeMapperList;
    }

    @Override
    public List<PostDetailVo> getPostDetail() {
        postDetailVoList= tbPostMapper.getPostDetail();
        for(PostDetailVo  postDetailVo:postDetailVoList){
            String reliability =redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postDetailVo.getPostId().toString());
            if (reliability!=null){
                postDetailVo.setReliability(postDetailVo.getReliability()+Integer.valueOf(reliability));
            }
        }
        return postDetailVoList;
    }

    @Override
    public List<PostDetailVo> getPostByCondition(ConditionVo conditionVo) {
        postDetailVoList=tbPostMapper.getPostByCondition(conditionVo);
        for(PostDetailVo  postDetailVo:postDetailVoList){
            String reliability =redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postDetailVo.getPostId().toString());
            if (reliability!=null){
                postDetailVo.setReliability(postDetailVo.getReliability()+Integer.valueOf(reliability));
            }
        }
        return postDetailVoList;
    }

}

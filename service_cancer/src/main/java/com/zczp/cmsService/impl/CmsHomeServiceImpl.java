package com.zczp.cmsService.impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.dao.TbPostTypeMapper;
import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.cmsService.CmsHomeService;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CmsHomeServiceImpl implements CmsHomeService {

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    @Autowired(required = false)
    private TbCityMapper tbCityMapper;

    @Autowired(required = false)
    private TbPostTypeMapper tbPostTypeMapper;

    List<PostDetailVo> postDetailVoList=null;

    @Override
    @Transactional
    public List<PostDetailVo> getPostDetail() {
        postDetailVoList= tbPostMapper.getPostDetail();
        return postDetailVoList;
    }

    @Override
    @Transactional
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
    public List<PostDetailVo> getPostByCondition(ConditionVo conditionVo) {
        postDetailVoList=tbPostMapper.getPostByCondition(conditionVo);
        return postDetailVoList;

    }

    @Override
    public Integer deleteByPrimaryKey(int postId) {
        return tbPostMapper.deleteByPrimaryKey(postId);
    }

    @Override
    public int getTotalTags() {
        return tbPostMapper.getTotalTags();
    }

}
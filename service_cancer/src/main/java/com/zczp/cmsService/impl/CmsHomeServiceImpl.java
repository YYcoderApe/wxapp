package com.zczp.cmsService.impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.dao.TbPostTypeMapper;
import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.cmsService.CmsHomeService;
import com.zczp.service_cancer.Impl.TbCollectServiceImpl;
import com.zczp.service_cancer.Impl.TbPostServiceImpl;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
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
    private TbPostServiceImpl tbPostService;

    @Autowired
    RedisUtil redisUtil;

    List<PostDetailVo> postDetailVoList=null;


    @Override
    public List<PostDetailVo> getPostByCondition(ConditionVo conditionVo) {
        tbPostService.transCountToDB();
        postDetailVoList=tbPostMapper.getPostsByCondition(conditionVo);
        for(PostDetailVo  postDetailVo:postDetailVoList){
            String reliability =redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postDetailVo.getPostId().toString());
            if (reliability!=null){
                postDetailVo.setReliability(postDetailVo.getReliability()+Integer.valueOf(reliability));
            }
        }
        return postDetailVoList;
    }

}

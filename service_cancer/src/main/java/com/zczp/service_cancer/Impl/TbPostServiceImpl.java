package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.TbPostService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TbPostServiceImpl implements TbPostService {
    @Autowired
    private TbPostMapper tbPostMapper;
    @Autowired
    private TbCommentServiceImpl tbCommentService;
    @Autowired
    private TbCollectServiceImpl tbCollectService;
    @Autowired
    private TbReliabilityServiceImpl tbReliabilityService;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public int deleteByPrimaryKey(Integer postId) {
        return 0;
    }

    @Override
    public int insert(TbPostWithBLOBs record) {
        return tbPostMapper.insert(record);
    }

    @Override
    public int insertSelective(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public TbPostWithBLOBs selectByPrimaryKey(Integer postId) {
        return tbPostMapper.selectByPrimaryKey(postId);
    }

    @Override
    public int updateByPrimaryKeySelective(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbPost record) {
        return 0;
    }

    @Override
    public PostDetailsVo selectDetailByPrimaryKey(Integer postId,String openId) {
        PostDetailsVo postDetailsVo=tbPostMapper.selectDetailByPrimaryKey(postId);
        List<CommentsVo> commentsVoList =tbCommentService.selectAllByPrimaryPostId(postDetailsVo.getPostId());
        for (CommentsVo commentVo:commentsVoList){
            commentVo.setCommentList(tbCommentService.selectAllByPrimaryReplyId(commentVo.getCommentId()));
        }
        postDetailsVo.setCommentsVoList(commentsVoList);
        String key =RedisKeyUtil.getKey(openId,postId);
        int reliabilityState;
        String sate=redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY,key);
        if (sate!=null){
             reliabilityState=Integer.valueOf(sate);
        }else {
            reliabilityState = tbReliabilityService.selectByPostIdAndUserId(postId,openId);
            redisUtil.hset(RedisKeyUtil.MAP_KEY_RELIABILITY,key,String.valueOf(reliabilityState));
        }
        postDetailsVo.setReliabilityState(reliabilityState);
        String reliability =redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postId.toString());
        if (reliability!=null){
            postDetailsVo.setReliability(postDetailsVo.getReliability()+Integer.valueOf(reliability));
        }
        return postDetailsVo;
    }

    @Override
    public List<PostDetailVo> selectByTitle(String title){
        return tbPostMapper.selectByTitle(title);
    }

    //更新可信度数到数据库
    public void transReliabilityCountToDB() {
        Map<String,String> reliabilityCount=redisUtil.hgetall(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,0);
        for (Map.Entry<String,String> entry:reliabilityCount.entrySet()){
            int postId=Integer.valueOf(entry.getKey());
            int count=Integer.valueOf(entry.getValue());
            TbPostWithBLOBs tbPostWithBLOBs=tbPostMapper.selectByPrimaryKey(postId);
            tbPostWithBLOBs.setReliability(tbPostWithBLOBs.getReliability()+count);
            tbPostMapper.updateReliabilityByPrimaryKey(tbPostWithBLOBs);
            redisUtil.hdel(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,entry.getKey());
        }
    }


    @Override
    public List<PostDetailVo> getPostDetail() {
        return null;
    }

    @Override
    public List<PostDetailVo> getPostByCityName(String cityName, String jobType, String postType) {
        return null;
    }

    @Override
    public List<TbPost> getAllPost() {
        return null;
    }
}

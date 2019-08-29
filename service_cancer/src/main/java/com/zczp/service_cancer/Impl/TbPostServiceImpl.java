package com.zczp.service_cancer.Impl;

import com.github.pagehelper.PageHelper;
import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.TbPostService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.util.TokenUtil;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_cancer.PostDetailsVo;
import com.zczp.vo_yycoder.PostDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TbPostServiceImpl implements TbPostService {
    @Autowired(required = false)
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
    public int insert(TbPostWithBLOBs record) {
        return tbPostMapper.insert(record);
    }


    @Override
    public TbPostWithBLOBs selectByPrimaryKey(Integer postId) {
        return tbPostMapper.selectByPrimaryKey(postId);
    }


    @Override
    public PostDetailsVo selectDetailByPrimaryKey(Integer postId,String openId,Integer pageNum) {
        PostDetailsVo postDetailsVo=tbPostMapper.selectDetailByPrimaryKey(postId);
        if (postDetailsVo==null) return null;
        //分页查询评论
        int pageSize=5;
        if (pageNum!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<CommentsVo> commentsVoList =tbCommentService.selectAllByPrimaryPostId(postDetailsVo.getPostId());
        for (CommentsVo commentVo:commentsVoList){
            commentVo.setCommentList(tbCommentService.selectAllByPrimaryReplyId(commentVo.getCommentId()));
        }
        postDetailsVo.setCommentsVoList(commentsVoList);
        if (openId!=null){
            String key =RedisKeyUtil.getKey(openId,postId);
            //查询可信度状态
            int reliabilityState;
            String sate=redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY,key);
            if (sate!=null){
                reliabilityState=Integer.valueOf(sate);
            }else {
                reliabilityState = tbReliabilityService.selectByPostIdAndUserId(postId,openId);
                redisUtil.hset(RedisKeyUtil.MAP_KEY_RELIABILITY,key,String.valueOf(reliabilityState));
            }
            postDetailsVo.setReliabilityState(reliabilityState);
            //查询可信度数
            String reliability =redisUtil.hget(RedisKeyUtil.MAP_KEY_RELIABILITY_COUNT,postId.toString());
            if (reliability!=null){
                postDetailsVo.setReliability(postDetailsVo.getReliability()+Integer.valueOf(reliability));
            }
            //查询收藏状态
            int collectState;
            String state1 = redisUtil.hget(RedisKeyUtil.MAP_KEY_COLLECT,key);
            if(state1!=null){
                collectState=Integer.valueOf(state1);
            }else {
                collectState =tbCollectService.selectByPostIdAndUserId(postId,openId);
                redisUtil.hset(RedisKeyUtil.MAP_KEY_COLLECT,key,String.valueOf(collectState));
            }
            postDetailsVo.setCollectState(collectState);
        }
        //查询评论数
        int totalCount=tbCommentService.getTotalTags(postId);
        postDetailsVo.setTotalCount(totalCount);
        //
        if (pageNum!=null) {
            postDetailsVo.setCurrPage(pageNum);
            postDetailsVo.setPageSize(pageSize);
            postDetailsVo.setTotalPage((int) Math.ceil((double) totalCount / pageSize));
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
    public List<PostDetailVo> selectByCompany(String company) {
//        String openId=new TokenUtil().getOpenId("Authorization");
//        if (openId!=null){
//             long count=redisUtil.lpush(0,"history_"+openId,company);
//             if (count>=12){
//                 redisUtil.rpop("history_"+openId,0);
//             }
//        }
        return tbPostMapper.selectByCompany(company);
    }

    @Override
    public int deletePostById(int postId) {
        return tbPostMapper.deleteByPrimaryKey(postId);
    }

//    @Override
//    public List<String> getSearchHistory() {
//        String openId=new TokenUtil().getOpenId("Authorization");
//        List<String> list=redisUtil.lrange("history_"+openId,0,11,0);
//        return list;
//    }
//
//    @Override
//    public void deleteHistory() {
//        String openId=new TokenUtil().getOpenId("Authorization");
//        for (int a=0;a<=11;a++){
//            redisUtil.lpop("history_"+openId);
//        }
//    }
}

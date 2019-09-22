package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbAskReplyMapper;
import com.zczp.dao.TbCommentMapper;
import com.zczp.entity.TbAskReply;
import com.zczp.service_cancer.TbCommentService;
import com.zczp.util.RedisKeyUtil;
import com.zczp.util.RedisUtil;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TbCommentServiceImpl implements TbCommentService {
    @Autowired(required = false)
    private TbCommentMapper tbCommentMapper;
    @Autowired(required = false)
    private TbAskReplyMapper tbAskReplyMapper;
    @Autowired
    private RedisUtil redisUtil;
    //提问时reply_id为null，回复时不为空
    public int insert(CommentVo commentVo) {
        TbAskReply tbAskReply=new TbAskReply(commentVo.getFromId(),commentVo.getPostId(),new Date());
        //查询是否有评论
        Integer result=tbAskReplyMapper.selectByPostIdAndOpenId(tbAskReply.getPostId(),tbAskReply.getOpenId());
        tbAskReply.setId(result);
        //更新被回复者时间
        if (commentVo.getReplyId()!=null){
            TbAskReply tbAskReply1=new TbAskReply(commentVo.getToId(),commentVo.getPostId(),new Date());
            Integer result1=tbAskReplyMapper.selectByPostIdAndOpenId(tbAskReply1.getPostId(),tbAskReply1.getOpenId());
            tbAskReply1.setId(result1);
            tbAskReplyMapper.updateByPrimaryKeySelective(tbAskReply1);
            if (redisUtil.exists(RedisKeyUtil.KEY_NEWS+":"+tbAskReply.getOpenId())){
                redisUtil.incr(RedisKeyUtil.KEY_NEWS+":"+tbAskReply.getOpenId());
            }else {
                redisUtil.set(RedisKeyUtil.KEY_NEWS+":"+tbAskReply.getOpenId(),"1",0);
            }
        }
        if (result==null){
            tbAskReplyMapper.insert(tbAskReply);
        }else {
            tbAskReplyMapper.updateByPrimaryKeySelective(tbAskReply);
        }
        return tbCommentMapper.insert(commentVo);
    }


    @Override
    public List<CommentsVo> selectAllByPrimaryPostId(Integer postId) {
        return tbCommentMapper.selectAllByPrimaryPostId(postId);
    }

    @Override
    public List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId) {
        return tbCommentMapper.selectAllByPrimaryReplyId(replyId);
    }

    @Override
    public Integer deleteByCommentId(int commentId) {
        return tbCommentMapper.deleteTbCommentById(commentId);
    }

    @Override
    public int getTotalTags(int postId) {
        return tbCommentMapper.getTotalTags(postId);
    }

    @Override
    public String getNewsCount(String openId) {
        String count;
        if(redisUtil.exists(RedisKeyUtil.KEY_NEWS+":"+openId)){
            count=redisUtil.get(RedisKeyUtil.KEY_NEWS+":"+openId,0);
        }else {
            count=null;
        }
        return count;
    }

    @Override
    public int delNewsCount(String openId) {
        int result;
        if(redisUtil.exists(RedisKeyUtil.KEY_NEWS+":"+openId)){
            redisUtil.del(RedisKeyUtil.KEY_NEWS+":"+openId);
            result =1;
        }else {
            result = 0;
        }
        return result;
    }
}

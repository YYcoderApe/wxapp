package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbAskReplyMapper;
import com.zczp.dao.TbCommentMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbAskReply;
import com.zczp.entity.TbComment;
import com.zczp.service_yycoder.AskReplyService;
import com.zczp.vo_yycoder.CollectPostDetailVo;
import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.TbCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AskReplyServiceImpl implements AskReplyService {
    @Autowired(required = false)
    private TbAskReplyMapper tbAskReplyMapper;

    @Autowired(required = false)
    private TbCommentMapper tbCommentMapper;

    @Autowired(required = false)
    private TbPostMapper tbPostMapper;

    //postDeatilVo（类，collectPostDeatailVo只是多了个state）
    CollectPostDetailVo collectPostDetailVo;

    //评论表，每个评论表含提问和回复
    List<TbCommentsVo> CommentsVoList=null;
    List<TbCommentsVo>  tbCommentsVoList=null;
    //评论表对象
    TbCommentsVo tbCommentsVo=new TbCommentsVo();

    @Override
    public List<MyAskReplyVo> getMyAskReplyList(String openId){
        TbComment tbComment=new TbComment();
        //含评论表和post表
        List<MyAskReplyVo> myAskReplyVoList = new ArrayList<MyAskReplyVo>();
        //根据open_id去TbAskReply找到post的先后顺序，返回TbAskReply
        List<TbAskReply> askReplyList = tbAskReplyMapper.getAskReplyByOpenId(openId);

        tbComment.setFromId(openId);
        for(TbAskReply tbAskReply:askReplyList){
            tbCommentsVoList=new ArrayList<TbCommentsVo>();
            tbComment.setPostId(tbAskReply.getPostId());
            CommentsVoList = tbCommentMapper.getCommentByPostId(tbComment.getPostId());
            int index=0;
            while(CommentsVoList.size()>index) {
                tbComment.setReplyId(CommentsVoList.get(index).getCommentId());
                tbComment.setToId(CommentsVoList.get(index).getFromId());
                tbCommentsVo.setCommentList(tbCommentMapper.selectCommentList(tbComment));

                CommentsVoList.get(index).setCommentList(tbCommentsVo.getCommentList());
                if(CommentsVoList.get(index).getCommentList().size()>0
                        | CommentsVoList.get(index).getFromId().equals(openId))
                    tbCommentsVoList.add(CommentsVoList.get(index));
                index++;
            }
            collectPostDetailVo=tbPostMapper.getPostDetailById(tbAskReply.getPostId());
            MyAskReplyVo myAskReplyVo =new MyAskReplyVo();
            myAskReplyVo.setPostDetailList(collectPostDetailVo);
            myAskReplyVo.setCommentList(tbCommentsVoList);
            myAskReplyVoList.add(myAskReplyVo);
        }
        return myAskReplyVoList;

    }

    @Override
    public List<MyAskReplyVo> getMyReplyMsgList(String openId) {
        TbComment tbComment=new TbComment();
        List<MyAskReplyVo> myAskReplyVoList = new ArrayList<MyAskReplyVo>();
        List<TbAskReply> askReplyList = tbAskReplyMapper.getAskReplyByOpenId(openId);
        tbComment.setFromId(openId);
        for(TbAskReply tbAskReply:askReplyList){
            tbCommentsVoList=new ArrayList<TbCommentsVo>();
            tbComment.setPostId(tbAskReply.getPostId());
            CommentsVoList = tbCommentMapper.selectTbCommentList(tbComment);
            int index=0;
            while(CommentsVoList.size()>index) {
                tbComment.setReplyId(CommentsVoList.get(index).getCommentId());
                tbComment.setToId(openId);
                tbCommentsVo.setCommentList(tbCommentMapper.selectCommentList(tbComment));
                CommentsVoList.get(index).setCommentList(tbCommentsVo.getCommentList());
                if(CommentsVoList.get(index).getCommentList().size()>0)
                    tbCommentsVoList.add(CommentsVoList.get(index));
                index++;
            }
            collectPostDetailVo=tbPostMapper.getPostDetailById(tbAskReply.getPostId());
            MyAskReplyVo myAskReplyVo =new MyAskReplyVo();
            myAskReplyVo.setPostDetailList(collectPostDetailVo);
            myAskReplyVo.setCommentList(tbCommentsVoList);
            if(myAskReplyVo.getCommentList()!=null & myAskReplyVo.getCommentList().size()!=0)
                myAskReplyVoList.add(myAskReplyVo);
        }
        return myAskReplyVoList;
    }

    @Override
    public int deleteTbCommentBycommentId(Integer commentId){
        return tbCommentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    @Transactional
    public int deleteTbComment(String openId, Integer postId) {
        List<Integer> list = tbCommentMapper.getAllCommentId(openId, postId);
        for(Integer index:list){
            tbCommentMapper.deleteTbCommentById(index);
        }
        tbAskReplyMapper.deleteById(openId, postId);
        return 1;
    }

}


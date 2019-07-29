package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCommentMapper;
import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.TbPostService;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_cancer.PostDetailVo;
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
    public PostDetailVo selectDetailByPrimaryKey(Integer postId) {
        PostDetailVo postDetailVo=tbPostMapper.selectDetailByPrimaryKey(postId);
        List<CommentsVo> commentsVoList =tbCommentService.selectAllByPrimaryPostId(postDetailVo.getPostId());
        for (CommentsVo commentVo:commentsVoList){
            commentVo.setCommentList(tbCommentService.selectAllByPrimaryReplyId(commentVo.getCommentId()));
        }
        postDetailVo.setCommentsVoList(commentsVoList);
        return postDetailVo;
    }
}

package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCommentMapper;
import com.zczp.entity.TbComment;
import com.zczp.service_cancer.TbCommentService;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TbCommentServiceImpl implements TbCommentService {
    @Autowired
    private TbCommentMapper tbCommentMapper;
    @Override
    public int deleteByPrimaryKey(Integer commentId) {
        return 0;
    }

    @Override
    public int insert(CommentVo commentVo) {
        return tbCommentMapper.insert(commentVo);
    }

    @Override
    public int insertSelective(TbComment record) {
        return 0;
    }

    @Override
    public TbComment selectByPrimaryKey(Integer commentId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbComment record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbComment record) {
        return 0;
    }

    @Override
    public List<CommentsVo> selectAllByPrimaryPostId(Integer postId) {
        return tbCommentMapper.selectAllByPrimaryPostId(postId);
    }

    @Override
    public List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId) {
        return tbCommentMapper.selectAllByPrimaryReplyId(replyId);
    }
}

package com.zczp.dao;

import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbCommentMapper extends BaseMapper<TbComment>{

    List<CommentsVo> selectAllByPrimaryPostId(Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId);
}
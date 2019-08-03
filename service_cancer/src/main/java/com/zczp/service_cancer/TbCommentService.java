package com.zczp.service_cancer;

import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;

import java.util.List;

public interface TbCommentService {

    int insert(CommentVo commentVo);

    List<CommentsVo> selectAllByPrimaryPostId(Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId);
}

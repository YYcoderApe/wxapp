package com.zczp.service_cancer;

import com.zczp.entity.TbComment;
import com.zczp.util.AjaxResult;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;

import java.util.List;

public interface TbCommentService  {

    int insert(CommentVo commentVo);

    List<CommentsVo> selectAllByPrimaryPostId(Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId);

    Integer deleteByCommentId(int commentId);

    int getTotalTags(int postId);

    String getNewsCount(String openId);

    int delNewsCount(String openId);
}

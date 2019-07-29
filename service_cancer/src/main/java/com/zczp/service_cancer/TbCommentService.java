package com.zczp.service_cancer;

import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;

import java.util.List;

public interface TbCommentService {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentVo commentVo);

    int insertSelective(TbComment record);

    TbComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(TbComment record);

    int updateByPrimaryKey(TbComment record);

    List<CommentsVo> selectAllByPrimaryPostId(Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId);
}

package com.zczp.dao;

import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentVo;
import com.zczp.vo_cancer.CommentsVo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbCommentMapper extends BaseMapper<TbComment>{
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentVo commentVo);

    int insertSelective(TbComment record);

    TbComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(TbComment record);

    int updateByPrimaryKey(TbComment record);

    List<CommentsVo> selectAllByPrimaryPostId(Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(Integer replyId);
}
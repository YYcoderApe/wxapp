package com.zczp.dao;

import com.zczp.entity.TbComment;
import com.zczp.vo_cancer.CommentsVo;
import com.zczp.vo_yycoder.TbCommentsVo;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface TbCommentMapper extends BaseMapper<TbComment>{
    List<CommentsVo> selectAllByPrimaryPostId(@Param("postId") Integer postId);

    List<CommentsVo> selectAllByPrimaryReplyId(@Param("replyId") Integer replyId);

    //查询提问
    List<TbCommentsVo> selectTbCommentList(TbComment tbComment);

    //查询回复
    List<TbCommentsVo> selectCommentList(TbComment tbComment);

    //获取评论信息
    List<TbCommentsVo> getCommentByPostId(@Param("postId") Integer postId);

    //删除评论
    int  deleteTbCommentById(@Param("commentId") Integer commentId);

    //根据openId和postId查找评论Id
    List<Integer> getAllCommentId(
            @Param("openId") String openId,
            @Param("postId") Integer postId);
}
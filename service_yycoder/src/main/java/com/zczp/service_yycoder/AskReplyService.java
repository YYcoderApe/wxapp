package com.zczp.service_yycoder;

import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.UserAskReplyVo;

import java.util.List;

public interface AskReplyService {
    //查看我的问一问（我的问答）
    List<MyAskReplyVo> getMyAskReplyList(String openId);

    //产看我的问一问（回复我的消息）
    List<MyAskReplyVo> getMyReplyMsgList(String openId);

    //删除评论信息(招聘信息未删除情况下)
    int deleteTbCommentBycommentId(Integer commentId);

    //删除评论信息(招聘信息已删除)
    int deleteTbComment(String openId,Integer postId);

    //后台查看我的问一问（我的问答）
    List<UserAskReplyVo> getUserAskReplyList(String openId);
}

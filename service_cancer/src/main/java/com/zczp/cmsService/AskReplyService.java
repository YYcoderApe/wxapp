package com.zczp.cmsService;

import com.zczp.vo_yycoder.MyAskReplyVo;

import java.util.List;

public interface AskReplyService {
    //查看我的问一问（我的问答）
    List<MyAskReplyVo> getMyAskReplyList(String openId);

    //产看我的问一问（回复我的消息）
    List<MyAskReplyVo> getMyReplyMsgList(String openId);

    //删除评论信息
    int deleteTbCommentBycommentId(Integer commentId);
}

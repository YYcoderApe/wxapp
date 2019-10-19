package com.zczp.service_yycoder;

import com.zczp.vo_yycoder.MyAskReplyVo;
import com.zczp.vo_yycoder.UserAskReplyVo;

import java.util.List;
import java.util.Map;

public interface AskReplyService {

    /**
     *   查看我的问一问（我的问答）
     * @param openId 用户的openId
     * @return  包含MyAskReplyVo的list列表
     */
    List<MyAskReplyVo> getMyAskReplyList(String openId);

    /**
     * 查看我的问一问（回复我的消息）
     * @param openId  用户的openId
     * @return 包含用户openId对应的消息list
     */
    List<MyAskReplyVo> getMyReplyMsgList(String openId);

    /**
     * 查看消息回复的数量
     * @param openId 用户的openId
     * @return Integer类型，表示消息回复数量
     */
    Integer getMyReplyCount(String openId);

    /**
     * 删除评论信息(招聘信息未删除情况下)
     * @param commentId 评论id
     * @return  0表示删除成功，1表示删除失败
     */
    int deleteTbCommentBycommentId(Integer commentId);

    /**
     * 删除评论信息(招聘信息已删除)
     * @param openId 用户Id
     * @param postId 招聘信息Id
     * @return 0表示删除成功，1表示删除失败
     */
    int deleteTbComment(String openId,Integer postId);

    /**
     *  后台管理系统：查看我的问一问（我的问答）
     * @param openId 用户openId
     * @return 用户的问答list
     */
    List<UserAskReplyVo> getUserAskReplyList(String openId);
}

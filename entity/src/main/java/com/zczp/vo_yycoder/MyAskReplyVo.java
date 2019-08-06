package com.zczp.vo_yycoder;

import java.util.List;

public class MyAskReplyVo {
    //获取postDeatailVo
    private CollectPostDetailVo PostDetailList;

    private List<TbCommentsVo> commentList;

    public CollectPostDetailVo getPostDetailList() {
        return PostDetailList;
    }

    public void setPostDetailList(CollectPostDetailVo postDetailList) {
        PostDetailList = postDetailList;
    }

    public List<TbCommentsVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<TbCommentsVo> commentList) {
        this.commentList = commentList;
    }
}

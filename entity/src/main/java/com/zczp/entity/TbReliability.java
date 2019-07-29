package com.zczp.entity;

public class TbReliability {
    public TbReliability(Integer postId, Integer userId) {
        this.postId = postId;
        this.userId = userId;
    }

    private Integer reliabilityId;

    private Integer postId;

    private Integer userId;

    public Integer getReliabilityId() {
        return reliabilityId;
    }

    public void setReliabilityId(Integer reliabilityId) {
        this.reliabilityId = reliabilityId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
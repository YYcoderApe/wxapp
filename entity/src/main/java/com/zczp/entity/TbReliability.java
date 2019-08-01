package com.zczp.entity;

public class TbReliability {
    private Integer reliabilityId;

    private Integer postId;

    private String openId;

    private Integer state;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
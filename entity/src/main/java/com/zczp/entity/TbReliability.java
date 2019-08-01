package com.zczp.entity;

public class TbReliability {
    public TbReliability(Integer postId, String openId, Integer state) {
        this.postId = postId;
        this.openId = openId;
        this.state = state;
    }

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
        this.openId = openId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
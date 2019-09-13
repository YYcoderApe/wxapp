package com.zczp.entity;

public class TbCollect {
    public TbCollect(Integer postId, String openId, Integer state) {
        this.postId = postId;
        this.openId = openId;
        this.state = state;
    }
    public TbCollect(){

    }

    private Integer collectId;

    private Integer postId;

    private String openId;

    private Integer state;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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
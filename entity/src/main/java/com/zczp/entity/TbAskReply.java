package com.zczp.entity;

import java.util.Date;

public class TbAskReply {
    private Integer id;

    private String openId;

    private Integer postId;

    private Date time;

    public TbAskReply(String openId, Integer postId, Date time) {
        this.openId = openId;
        this.postId = postId;
        this.time = time;
    }
    public  TbAskReply(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TbAskReply{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", postId=" + postId +
                ", time=" + time +
                '}';
    }
}
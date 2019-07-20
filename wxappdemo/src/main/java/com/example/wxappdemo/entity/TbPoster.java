package com.example.wxappdemo.entity;

public class TbPoster {
    private Integer posterId;

    private String posterAddress;

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getPosterAddress() {
        return posterAddress;
    }

    public void setPosterAddress(String posterAddress) {
        this.posterAddress = posterAddress == null ? null : posterAddress.trim();
    }
}
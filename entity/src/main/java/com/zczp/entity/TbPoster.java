package com.zczp.entity;

public class TbPoster {
    private Integer posterId;

    private String posterImage;

    private String posterAddress;

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage == null ? null : posterImage.trim();
    }

    public String getPosterAddress() {
        return posterAddress;
    }

    public void setPosterAddress(String posterAddress) {
        this.posterAddress = posterAddress == null ? null : posterAddress.trim();
    }
}
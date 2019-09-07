package com.zczp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbComment {
    @ApiModelProperty("评论Id")
    private Integer commentId;

    @ApiModelProperty("招聘信息Id")
    private Integer postId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户ID")
    private String fromId;

    @ApiModelProperty("回复的用户ID")
    private String toId;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date commentTime;

    @ApiModelProperty("回复的评论ID")
    private Integer replyId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId == null ? null : fromId.trim();
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return "TbComment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", commentTime=" + commentTime +
                ", replyId=" + replyId +
                '}';
    }
}
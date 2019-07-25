package com.zczp.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class PostDetailVo {
    private Integer postId;

    private String postType;

    private Integer companyName;

    private String jobType;

    private Integer reliability;

    private Integer cityName;

    private Date postTime;

    private String internshipTime;

    private Date entryTime;

    private String deliveryMethod;

    private Integer commentId;

    private Integer userName;

    private Integer state;

    private String title;

    private String requirement;

    private  List<CommentsVo> commentsVoList;
}

package com.zczp.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class CommentsVo {
    private Integer commentId;

    private Integer postId;

    private String content;

    private Integer userName;

    private Integer toId;

    private Date commentTime;

    private List<CommentsVo> commentList;
}

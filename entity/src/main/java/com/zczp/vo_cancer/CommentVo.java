package com.zczp.vo_cancer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zczp.entity.TbComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class CommentVo {

    @ApiModelProperty("招聘信息Id")
    private Integer postId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户ID")
    private String fromId;

    @ApiModelProperty("回复的用户ID")
    private String toId;

    @ApiModelProperty("评论时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    @ApiModelProperty("回复的评论ID")
    private Integer replyId;
}

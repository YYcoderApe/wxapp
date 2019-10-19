package com.zczp.vo_yycoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *@author: cancer
 *@data: 2019/7/26 15:01
 *@descriptions: 提问回复类
 *@version: 1.0
 */
@Data
public class TbCommentsVo {
    @ApiModelProperty("评论Id")
    private Integer commentId;

    @ApiModelProperty("招聘信息Id")
    private Integer postId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户ID")
    private String fromId;

    @ApiModelProperty("用户图片")
    private String userImage;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date commentTime;

    @ApiModelProperty("回复的评论ID")
    private Integer replyId;

    @ApiModelProperty("回复数量状态（0~1条返回0，1条以上返回1）")
    private int replyState;

    @ApiModelProperty("回复列表")
    private List<TbCommentsVo> commentList;


}

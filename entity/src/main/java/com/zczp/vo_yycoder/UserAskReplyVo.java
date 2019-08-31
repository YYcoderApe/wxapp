package com.zczp.vo_yycoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserAskReplyVo {

    @ApiModelProperty("postId")
    private Integer postId;

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("岗位类型")
    private String postType;

    @ApiModelProperty("招聘类型")
    private String jobType;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date postTime;

    @ApiModelProperty("招聘表发布状态（-1:已删除 0:待审核 1:已审核 ）")
    private Integer state;

    @ApiModelProperty("评论Id")
    private Integer commentId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("0表示提问 1表示回复")
    private Integer isReply;

}
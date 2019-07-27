package com.zczp.vo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *@author: cancer
 *@data: 2019/7/26 14:04
 *@descriptions: 招聘信息详情类
 *@version: 1.0
 */
@Data
public class PostDetailVo {
   @ApiModelProperty("岗位ID")
    private Integer postId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("岗位要求")
    private String requirement;

    @ApiModelProperty("岗位类型")
    private String typeName;

    @ApiModelProperty("发布用户ID")
    private Integer userId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("招聘类型（1-实习 2-校招）")
    private String jobType;

    @ApiModelProperty("可信度")
    private Integer reliability;

    @ApiModelProperty("城市")
    private String cityName;

    @ApiModelProperty("发布时间")
    private Date postTime;

    @ApiModelProperty("实习时长")
    private String internshipTime;

    @ApiModelProperty("入职时间")
    private Date entryTime;

    @ApiModelProperty("投递方式")
    private String deliveryMethod;

    @ApiModelProperty("状态（0-待审核 1-通过审核 2-已删除）")
    private Integer state;

    @ApiModelProperty("评论列表")
    private  List<CommentsVo> commentsVoList;
}

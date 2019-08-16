package com.zczp.vo_cancer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 *@author: cancer
 *@data: 2019/7/26 14:04
 *@descriptions: 招聘信息详情类
 *@version: 1.0
 */
@Data
public class PostDetailsVo {
   @ApiModelProperty("岗位ID")
    private Integer postId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("岗位要求")
    private String requirement;

    @ApiModelProperty("岗位类型")
    private String typeName;

    @ApiModelProperty("发布用户ID")
    private Integer openId;

    @ApiModelProperty("公司名称")
    private String companyName;
//图片获取
    @ApiModelProperty("公司logo")
    private String companyLogo;

    @ApiModelProperty("招聘类型（实习 校招）")
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
    private String entryTime;

    @ApiModelProperty("投递方式")
    private String deliveryMethod;

    @ApiModelProperty("状态（0-待审核 1-通过审核 2-已删除）")
    private Integer state;

    @ApiModelProperty("招聘描述")
    private String jobDescription;

    @ApiModelProperty("工作地址")
    private String workAddress;

    @ApiModelProperty("可信度Id 0-未点击可信 1-已点击")
    private int reliabilityState;

    @ApiModelProperty("收藏Id 0-未收藏 1-已收藏")
    private int collectState;

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //当前页数
    private int currPage;
    //总页数
    private int totalPage;

    @ApiModelProperty("评论列表")
    private List<CommentsVo> commentsVoList;

}

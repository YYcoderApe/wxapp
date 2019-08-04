package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PostDetailVo {
    @ApiModelProperty("postId")
    private Integer postId;

    @ApiModelProperty("招聘的title")
    private String title;

    @ApiModelProperty("岗位类型")
    private String postType;

    @ApiModelProperty("公司logo")
    private String companyLogo;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("招聘类型")
    private String jobType;

    @ApiModelProperty("可信度")
    private Integer reliability;

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("发布时间")
    private Date postTime;

    @ApiModelProperty("实习时长")
    private String internshipTime;

    @ApiModelProperty("入职时间")
    private String entryTime;

}

package com.zczp.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TbPost {
    @ApiModelProperty("岗位ID")
    private Integer postId;

    @ApiModelProperty("岗位类型Id")
    private Integer typeId;

    @ApiModelProperty("公司Id")
    private Integer companyId;

    @ApiModelProperty("招聘类型（实习 校招）")
    private String jobType;

    @ApiModelProperty("可信度")
    private Integer reliability;

    @ApiModelProperty("城市Id")
    private Integer cityId;

    @ApiModelProperty("发布时间")
    private Date postTime;

    @ApiModelProperty("入职时间")
    private String entryTime;

    @ApiModelProperty("实习时长")
    private String internshipTime;

    @ApiModelProperty("投递方式")
    private String deliveryMethod;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("发布用户ID")
    private Integer userId;

    @ApiModelProperty("状态 0-待审核")
    private Integer state;

    @ApiModelProperty("岗位要求")
    private String requirement;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public Integer getReliability() {
        return reliability;
    }

    public void setReliability(Integer reliability) {
        this.reliability = reliability;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime == null ? null : entryTime.trim();
    }

    public String getInternshipTime() {
        return internshipTime;
    }

    public void setInternshipTime(String internshipTime) {
        this.internshipTime = internshipTime == null ? null : internshipTime.trim();
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod == null ? null : deliveryMethod.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }
}
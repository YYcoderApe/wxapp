package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于作为条件筛选
 *
 */
public class ConditionVo {

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("招聘类型")
    private String jobType;

    @ApiModelProperty("岗位类型")
    private String postType;

    @ApiModelProperty("公司名称")
    private String companyName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

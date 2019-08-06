package com.zczp.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbPostWithBLOBs extends TbPost {
    @ApiModelProperty("招聘描述")
    private String jobDescription;
    @ApiModelProperty("工作地址")
    private String workAddress;

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription == null ? null : jobDescription.trim();
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress == null ? null : workAddress.trim();
    }
}
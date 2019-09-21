package com.zczp.vo_cancer;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CompanyVo {
    @ApiModelProperty("公司Id")
    private Integer companyId;

    @ApiModelProperty("公司名")
    private String companyName;

    @ApiModelProperty("公司logo")
    private String companyLogo;

    @ApiModelProperty("搜索次数")
    private Integer count;

    @ApiModelProperty("状态")
    private Integer companyState;
}

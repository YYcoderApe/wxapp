package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoVo {

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userImage;

    @ApiModelProperty("用户所在学校")
    private String userSchool;

    @ApiModelProperty("所在公司名称")
    private String userCompany;

    @ApiModelProperty("职位类型")
    private String userPosttype;

    @ApiModelProperty("用户联系方式")
    private String userPhone;

    @ApiModelProperty("个性签名")
    private String userSignature;

}

package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDetailVo {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户头像")
    private String userImage;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户性别")
    private String userGender;


    @ApiModelProperty("用户联系方式")
    private String userPhone;

    @ApiModelProperty("用户所在学校")
    private String userSchool;

    @ApiModelProperty("所在公司名称")
    private String userCompany;

    @ApiModelProperty("职位类型")
    private String postType;

    @ApiModelProperty("个性签名")
    private Integer userSignature;

}

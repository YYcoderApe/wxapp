package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserDetailVo extends UserInfoVo{

    @ApiModelProperty("用户id")
    private String openId;

    @ApiModelProperty("用户头像")
    private String userImage;

    @ApiModelProperty("用户性别")
    private String userGender;

    @ApiModelProperty("用户状态 0:用户 1:机器人")
    private Integer state;


}

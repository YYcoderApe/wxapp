package com.zczp.vo_yycoder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于作为条件筛选
 *
 * @author YYcoder
 */
@Data
public class ConditionVo {

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("招聘类型")
    private String jobType;

    @ApiModelProperty("岗位类型")
    private String postType;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("排序方式")
    private String sortWay;


}

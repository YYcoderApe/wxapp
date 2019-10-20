package com.zczp.cmsService;


import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;
import com.zczp.vo_yycoder.ConditionVo;
import com.zczp.vo_yycoder.PostDetailVo;

import java.util.List;

public interface CmsHomeService {


    //根据城市cityName 进行字段查询
    List<PostDetailVo> getPostByCondition(ConditionVo conditionVo);
}

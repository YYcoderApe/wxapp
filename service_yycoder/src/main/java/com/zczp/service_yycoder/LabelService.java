package com.zczp.service_yycoder;

import com.zczp.entity.TbCity;
import com.zczp.entity.TbPostType;

public interface LabelService {

    //新增城市标签
    int addCityLabel(String cityName);

    //编辑城市标签
    int updateCityLabel(TbCity tbCity);

    //删除城市标签
    int deleteCityLabel(Integer cityId);

    //新增岗位标签
    int addPostTypeLabel(String typeName);

    //编辑岗位标签
    int updatePostTypeLabel(TbPostType tbPostType);

    //删除岗位标签
    int deletePostTypeLabel(Integer typeId);
}

package com.zczp.dao;

import com.zczp.entity.TbCompany;

public interface TbCompanyMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(TbCompany record);

    int insertSelective(TbCompany record);

    TbCompany selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(TbCompany record);

    int updateByPrimaryKey(TbCompany record);
}
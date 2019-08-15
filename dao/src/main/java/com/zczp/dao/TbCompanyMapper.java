package com.zczp.dao;

import com.zczp.entity.TbCompany;
import com.zczp.vo_cancer.CompanyVo;

import java.util.List;

public interface TbCompanyMapper extends BaseMapper<TbCompany>{
    List<CompanyVo> selectByName(String companyName);

    //查询搜索次数前12公司
    List<CompanyVo> selectByCount();

    List<CompanyVo> selectAll();

    Integer addCompany(CompanyVo companyVo);
}
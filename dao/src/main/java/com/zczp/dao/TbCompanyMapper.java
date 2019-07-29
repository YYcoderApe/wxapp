package com.zczp.dao;

import com.zczp.entity.TbCompany;
import com.zczp.vo_cancer.CompanyVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbCompanyMapper extends BaseMapper<TbCompany>{
    List<CompanyVo> selectByName(String companyName);

    //查询搜索次数前12公司
    List<CompanyVo> selectByCount();
}
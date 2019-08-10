package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCompanyMapper;
import com.zczp.service_cancer.TbCompanyService;
import com.zczp.vo_cancer.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbCompanyServiceImpl implements TbCompanyService {
    @Autowired(required = false)
    private TbCompanyMapper tbCompanyMapper;
    @Override
    public List<CompanyVo> selectByName(String companyName) {
        return tbCompanyMapper.selectByName(companyName);
    }

    //查询搜索次数前12公司
    public List<CompanyVo> selectByCount() {
        return tbCompanyMapper.selectByCount();
    }
}

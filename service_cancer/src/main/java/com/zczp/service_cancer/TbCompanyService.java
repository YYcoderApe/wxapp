package com.zczp.service_cancer;

import com.zczp.vo_cancer.CompanyVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TbCompanyService {
    List<CompanyVo> selectByName(String companyName);

    List<CompanyVo> selectByCount();

    List<CompanyVo> selectAll();

    Integer addCompany(CompanyVo companyVo);

    Integer updateCompany(CompanyVo companyVo);

    Integer deleteCompanyById(int companyId);

    void importCompany(MultipartFile file);

    int getTotalTags();

    int getSearchTags(String companyName);
}

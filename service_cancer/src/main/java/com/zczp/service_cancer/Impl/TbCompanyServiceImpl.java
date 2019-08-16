package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCompanyMapper;
import com.zczp.service_cancer.TbCompanyService;
import com.zczp.vo_cancer.CompanyVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public List<CompanyVo> selectAll() {
        return tbCompanyMapper.selectAll();
    }

    @Override
    public Integer addCompany(CompanyVo companyVo) {
        return tbCompanyMapper.addCompany(companyVo);
    }

    @Override
    public Integer updateCompany(CompanyVo companyVo) {
        return tbCompanyMapper.updateByPrimaryKeySelective(companyVo);
    }

    @Override
    public Integer deleteCompanyById(int companyId) {
        return tbCompanyMapper.deleteByPrimaryKey(companyId);
    }

    @Override
    public void importCompany(MultipartFile file) {
        try {
            XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
            //根据名称获得指定Sheet对象
            XSSFSheet xssfSheet = workbook.getSheetAt(0);
            for (Row row : xssfSheet) {
                CompanyVo companyVo=new CompanyVo();
                int rowNum = row.getRowNum();
                if(rowNum == 0){//跳出第一行   一般第一行都是表头没有数据意义
                    continue;
                }
                if(row.getCell(1)!=null){//第2列数据
                    companyVo.setCompanyName(row.getCell(1).getStringCellValue());
                }
                if(row.getCell(2)!=null){//第3列
                   companyVo.setCompanyLogo(row.getCell(2).getStringCellValue());
                }
//                System.out.println(companyVo.toString());
               tbCompanyMapper.addCompany(companyVo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.entity.TbCity;
import com.zczp.service_cancer.TbCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbCityServiceImpl implements TbCityService {
    @Autowired
    private TbCityMapper tbCityMapper;


    @Override
    public List<TbCity> selectAll() {
        return tbCityMapper.selectAll();
    }
}

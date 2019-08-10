package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCityMapper;
import com.zczp.entity.TbCity;
import com.zczp.service_cancer.TbCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbCityServiceImpl implements TbCityService {
    @Autowired(required = false)
    private TbCityMapper tbCityMapper;
    @Override
    public int deleteByPrimaryKey(Integer cityId) {
        return 0;
    }

    @Override
    public int insert(TbCity record) {
        return 0;
    }

    @Override
    public int insertSelective(TbCity record) {
        return 0;
    }

    @Override
    public TbCity selectByPrimaryKey(Integer cityId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbCity record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbCity record) {
        return 0;
    }

    @Override
    public List<TbCity> selectAll() {
        return tbCityMapper.selectAll();
    }
}

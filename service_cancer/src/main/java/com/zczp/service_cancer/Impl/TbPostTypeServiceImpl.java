package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbPostTypeMapper;
import com.zczp.entity.TbPostType;
import com.zczp.service_cancer.TbPostService;
import com.zczp.service_cancer.TbPostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TbPostTypeServiceImpl implements TbPostTypeService {
    @Autowired
    private TbPostTypeMapper tbPostTypeMapper;
    @Override
    public List<TbPostType> selectAll() {
        return tbPostTypeMapper.selectAll();
    }
}

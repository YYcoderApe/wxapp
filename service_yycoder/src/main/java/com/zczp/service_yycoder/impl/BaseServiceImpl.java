package com.zczp.service_yycoder.impl;
;
import com.zczp.dao.BaseMapper;
import com.zczp.service_yycoder.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<M extends BaseMapper<T>,T> implements BaseService<T> {
    @Autowired
    protected M baseMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id){return baseMapper.deleteByPrimaryKey(id);}

    @Override
    @Transactional
    public int insert(T record){return baseMapper.insert(record);}

    @Override
    @Transactional
    public int insertSelective(T record){return baseMapper.insertSelective(record);}

    @Override
    public T selectByPrimaryKey(Integer id){return baseMapper.selectByPrimaryKey(id);}

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(T record){return baseMapper.updateByPrimaryKeySelective(record);}

    @Override
    @Transactional
    public int updateByPrimaryKey(T record){return baseMapper.updateByPrimaryKey(record);}
}

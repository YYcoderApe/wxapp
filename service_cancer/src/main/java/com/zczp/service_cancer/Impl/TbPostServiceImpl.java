package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbPostMapper;
import com.zczp.entity.TbPost;
import com.zczp.entity.TbPostWithBLOBs;
import com.zczp.service_cancer.TbPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbPostServiceImpl implements TbPostService {
    @Autowired
    private TbPostMapper tbPostMapper;
    @Override
    public int deleteByPrimaryKey(Integer postId) {
        return 0;
    }

    @Override
    public int insert(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public int insertSelective(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public TbPostWithBLOBs selectByPrimaryKey(Integer postId) {
        return tbPostMapper.selectByPrimaryKey(postId);
    }

    @Override
    public int updateByPrimaryKeySelective(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(TbPostWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbPost record) {
        return 0;
    }
}

package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbReliabilityMapper;
import com.zczp.entity.TbReliability;
import com.zczp.service_cancer.TbReliabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbReliabilityServiceImpl implements TbReliabilityService {
    @Autowired
    private TbReliabilityMapper tbReliabilityMapper;
    @Override
    public int deleteByPrimaryKey(Integer reliabilityId) {
        return 0;
    }

    @Override
    public int insert(TbReliability record) {
        return 0;
    }

    @Override
    public int insertSelective(TbReliability record) {
        return 0;
    }

    @Override
    public TbReliability selectByPrimaryKey(Integer reliabilityId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbReliability record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbReliability record) {
        return 0;
    }

    @Override
    public Integer selectByPostIdAndUserId(int postId, int userId) {
        Integer reliabilityId =tbReliabilityMapper.selectByPostIdAndUserId(postId,userId);
        if (reliabilityId!=null){
            return reliabilityId;
        }
        return 0;
    }
}

package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbCollectMapper;
import com.zczp.entity.TbCollect;
import com.zczp.service_cancer.TbCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbCollectServiceImpl implements TbCollectService {
    @Autowired
    private TbCollectMapper tbCollectMapper;
    @Override
    public int deleteByPrimaryKey(Integer collectId) {
        return tbCollectMapper.deleteByPrimaryKey(collectId);
    }

    @Override
    public int insert(TbCollect record) {
        return tbCollectMapper.insert(record);
    }

    @Override
    public int insertSelective(TbCollect record) {
        return tbCollectMapper.insertSelective(record);
    }

    @Override
    public TbCollect selectByPrimaryKey(Integer collectId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TbCollect record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TbCollect record) {
        return 0;
    }

    @Override
    public Integer selectByPostIdAndUserId(Integer postId, String openId) {
        Integer collectId = tbCollectMapper.selectByPostIdAndUserId(postId,openId);
        if (collectId!=null){
            return collectId;
        }
        return 0;
    }
}

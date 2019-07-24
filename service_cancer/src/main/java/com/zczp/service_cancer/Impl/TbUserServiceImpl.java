package com.zczp.service_cancer.Impl;

import com.zczp.dao.TbUserMapper;
import com.zczp.entity.TbUser;
import com.zczp.service_cancer.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser selectByPrimaryKey(Integer userId) {
        return tbUserMapper.selectByPrimaryKey(userId);
    }
}

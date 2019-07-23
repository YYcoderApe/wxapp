package com.example.wxappdemo.service.Impl;

import com.example.wxappdemo.entity.TbUser;
import com.example.wxappdemo.mapper.TbUserMapper;
import com.example.wxappdemo.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser selectbyid(int id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }
}

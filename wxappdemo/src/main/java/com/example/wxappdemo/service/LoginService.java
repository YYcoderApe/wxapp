package com.example.wxappdemo.service;

import com.example.wxappdemo.entity.TbUser;

public interface LoginService {
    TbUser selectbyid(int id);
}

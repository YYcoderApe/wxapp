package com.zczp.service_yycoder.impl;

import com.zczp.dao.TbAdminMapper;
import com.zczp.entity.TbAdmin;
import com.zczp.service_yycoder.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private TbAdminMapper tbAdminMapper;
    @Override
    public boolean checkAdmin(String username, String password) {
        //检查用户名
        TbAdmin tbAdmin=tbAdminMapper.checkAdmin(username,password);
        if(tbAdmin!=null)
            return true;
        return false;
    }
}

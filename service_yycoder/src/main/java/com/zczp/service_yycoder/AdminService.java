package com.zczp.service_yycoder;

import com.zczp.entity.TbAdmin;

public interface AdminService {

    //根据用户名和密码进行登录
    boolean checkAdmin(String username, String password);
}

package com.zczp.service_yycoder;

public interface AdminService {

    //根据用户名和密码进行登录
    boolean checkAdmin(String username, String password);
}

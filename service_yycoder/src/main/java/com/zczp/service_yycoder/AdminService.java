package com.zczp.service_yycoder;

public interface AdminService {

    /**
     * 用于查询账户和密码，是否存在
     *
     * @param username 用户名
     * @param password 用户密码
     * @return boolean类型，1表示有数据，0表示没有
     */
    boolean checkAdmin(String username, String password);
}

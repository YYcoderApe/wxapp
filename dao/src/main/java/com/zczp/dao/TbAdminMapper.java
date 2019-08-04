package com.zczp.dao;

import com.zczp.entity.TbAdmin;
import org.apache.ibatis.annotations.Param;

public interface TbAdminMapper extends BaseMapper<TbAdmin>{
    TbAdmin checkAdmin(
            @Param("adminName") String adminName,
            @Param("adminPassword") String adminPassword);
}
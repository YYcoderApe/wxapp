package com.zczp.service_yycoder;

import com.zczp.dao.DaoTest;

public class ServiceTest {
    public String showService(){
        DaoTest daoTest = new DaoTest();
        return daoTest.shwoDao()+" 我是service ";
    }

}

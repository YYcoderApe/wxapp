package com.zczp.service_cancer;

import com.zczp.dao.DaoTest;

public class ServiceTest {
    public String showService(){
        DaoTest daoTest = new DaoTest();
        return daoTest.shwoDao()+" 我是service ";
    }

}

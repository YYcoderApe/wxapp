package com.zczp.dao;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zczp.entity.EntityTest;

public class DaoTest {
    public String shwoDao(){
        EntityTest entityTest = new EntityTest();
        return entityTest.showEntity()+" 我是dao ";

    }

}

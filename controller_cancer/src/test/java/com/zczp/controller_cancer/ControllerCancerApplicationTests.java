package com.zczp.controller_cancer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControllerCancerApplicationTests.class)
public class ControllerCancerApplicationTests {

    @Test
    public void contextLoads() {
        String uuid=UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
    }

}

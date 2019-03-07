package com.hack.service.impl;

import com.hack.entity.UserLogin;
import com.hack.service.UserLoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserLoginServiceImplTest {

    private ApplicationContext applicationContext;
    UserLoginService userLoginService;
    @Before
    public void setUp() throws Exception{
        applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml");

        userLoginService= (UserLoginService) applicationContext.getBean("userLoginServiceImpl");
    }

    @Test
    public void findByName()throws Exception {
        UserLogin u=userLoginService.findByName("101");
        System.out.println(u);
    }

    @Test
    public void save() {
    }

    @Test
    public void removeByName() {
    }

    @Test
    public void updateByName() {
    }
}
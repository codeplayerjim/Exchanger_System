package com.hack.service.impl;

import com.hack.entity.UserLogin;
import com.hack.entity.UserLoginExample;
import com.hack.mapper.UserLoginMapper;
import com.hack.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper ;


    @Override
    public UserLogin findByName(String name) throws Exception {
        UserLoginExample userLoginExample=new UserLoginExample();
        UserLoginExample.Criteria criteria=userLoginExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<UserLogin>list=userLoginMapper.selectByExample(userLoginExample);
        return list.get(0);
    }

    @Override
    public void save(UserLogin userlogin) throws Exception {
        userLoginMapper.insert(userlogin);
    }

    @Override
    public void removeByName(String name) throws Exception {
            UserLoginExample userLoginExample=new UserLoginExample();
            UserLoginExample.Criteria criteria=userLoginExample.createCriteria();
            userLoginMapper.deleteByExample(userLoginExample);

    }

    @Override
    public void updateByName(String name, UserLogin userlogin) {
        UserLoginExample userLoginExample=new UserLoginExample();
        UserLoginExample.Criteria criteria=userLoginExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        userLoginMapper.updateByExample(userlogin,userLoginExample);
    }
}


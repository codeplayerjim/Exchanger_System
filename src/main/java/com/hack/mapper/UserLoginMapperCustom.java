package com.hack.mapper;

import com.hack.entity.UserLoginCustom;

public interface UserLoginMapperCustom {
    UserLoginCustom findOneByName(String name)throws Exception;
}

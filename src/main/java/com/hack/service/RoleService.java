package com.hack.service;

import com.hack.entity.Role;

public interface RoleService {
    Role findById(Integer id)throws Exception;
}

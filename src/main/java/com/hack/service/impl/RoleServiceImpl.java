package com.hack.service.impl;

import com.hack.entity.Role;
import com.hack.mapper.RoleMapper;
import com.hack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }
}

package com.hack.entity;

/**
    UserLogin扩展类
 */
public class UserLoginCustom extends UserLogin{
    private Role role_ob;

    public Role getRole_ob() {
        return role_ob;
    }

    public void setRole_ob(Role role_ob) {
        this.role_ob = role_ob;
    }
}

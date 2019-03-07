package com.hack.entity;

public class Role {
    private Integer roleid;
    private String rolename;
    private String permissions;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions==null ? null:permissions.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename==null?null:rolename.trim();
    }
}

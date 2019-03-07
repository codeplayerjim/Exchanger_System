package com.hack.realm;

import com.hack.entity.Role;
import com.hack.entity.UserLogin;
import com.hack.service.RoleService;
import com.hack.service.UserLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) getAvailablePrincipal(principalCollection);
        Role role=null;

        try {
            //获取角色对象
            UserLogin userLogin=userLoginService.findByName(username);
            role=roleService.findById(userLogin.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String>r=new HashSet<String>();
        if(role!=null){
            r.add(role.getRolename());
            info.setRoles(r);
        }



        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password=new String((char[])authenticationToken.getCredentials());

        UserLogin userLogin=null;

        try {
            userLogin=userLoginService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(userLogin==null){
            throw new UnknownAccountException();
        }else if(!password.equals(userLogin.getPassword())){
            throw new IncorrectCredentialsException();
        }

        //身份验证，返回一个身份信息
        AuthenticationInfo info=new SimpleAuthenticationInfo(username,password,getName());


        return info;
    }
}

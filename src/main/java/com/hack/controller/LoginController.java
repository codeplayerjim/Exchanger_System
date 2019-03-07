package com.hack.controller;

import com.hack.entity.UserLogin;
import com.hack.service.impl.UserLoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    //跳转
    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public String loginUI()throws Exception{
        return "../../login";
    }

    //表单处理
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(UserLogin userLogin)throws Exception{
        //shiro
        UsernamePasswordToken token=new UsernamePasswordToken(userLogin.getUsername(),
                userLogin.getPassword());
        Subject subject= SecurityUtils.getSubject();
        subject.login(token);

        if(subject.hasRole("admin")){
            return "redirect:/admin/showExchanger";
        }else if (subject.hasRole("exchanger")){
            return "redirect:/exchanger/showCourse";
        }

        return "/login";
    }

}

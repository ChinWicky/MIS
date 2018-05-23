package com.example.demo.web;


import com.example.demo.entity.SysUser;
import com.example.demo.entity.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class GeneralController extends GlobalMethodSecurityConfiguration {
    @Autowired
    private TypeService typeService;
    public  GeneralController(){

    }
//    public Model returnAllType(Model model){
//        Map map=new HashMap();
//        java.util.List<Type> types = typeService.selectByMap(map);
//        Type type=types.get(0);
//        System.out.println(type.getTypename());
//        model.addAttribute("types",types);
//        return model;
//
//    }

    public Model returnUser(Model model){
        HttpServletRequest request1= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return  model;

    }



}

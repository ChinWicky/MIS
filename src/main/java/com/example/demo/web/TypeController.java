package com.example.demo.web;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/type")
public class TypeController extends GlobalMethodSecurityConfiguration{
    @Autowired
    private TypeService typeService;


    @RequestMapping("/add")
    public String addType(HttpServletRequest request,Model model){
        String typeName = request.getParameter("typename");
        Type type=new Type();
        type.setTypename(typeName);
       this.typeService.insert(type);
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "type/type_list";

    }

    @RequestMapping("/toAdd")
    public String ToaddType(HttpServletRequest request,Model model){
        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "type/add_type";

    }


    @RequestMapping("/delete")
    public String deleteType(int typeid,Model model){
        this.typeService.deleteById(typeid);
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "type/type_list";
        }


//修改成功之后返回列表
    @RequestMapping("/edit")
    public String editType(HttpServletRequest request,Model model){
       int typeid = Integer.parseInt(request.getParameter("typeid"));
       String typename = request.getParameter("typename");
        Type type=new Type();
        type.setTypeid(typeid);
        type.setTypename(typename);
        typeService.updateById(type);
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "type/type_list";
    }

    //修改种类名称
    @RequestMapping("/toEdit")
    public String toEditType(HttpServletRequest request,Model model){
        int typeid = Integer.parseInt(request.getParameter("typeid"));
        Type type=new Type();
        type=typeService.selectById(typeid);
        model.addAttribute("type",type);
        model.addAttribute("typeid",typeid);
  //      System.out.print("ToEdit"+typeid);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
       return "type/edit_type";

    }
//访问所有type列表

    @RequestMapping("/list")
    public String showType(Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
       return "type/type_list";

    }





}

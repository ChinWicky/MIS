package com.example.demo.web;

import com.example.demo.entity.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        String typename = request.getParameter("type");
        Type type=new Type();
        type.setTypename(typename);
       this.typeService.insert(type);

        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        return "type/type_list";

    }

    @RequestMapping("/toAdd")
    public String ToaddType(HttpServletRequest request,Model model){
        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        return "type/add_type";

    }


    @RequestMapping("/delete")
    public String deleteType(int typeid,Model model){
        this.typeService.deleteById(typeid);
        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
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

        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
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

        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
       return "type/edit_type";

    }
//访问所有type列表

    @RequestMapping("/list")
    public String showType(Model model){
        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
       return "type/type_list";

    }

    public Model returnAllType(Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        return model;

    }



}

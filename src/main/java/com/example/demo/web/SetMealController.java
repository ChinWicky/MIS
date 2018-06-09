package com.example.demo.web;


import com.example.demo.entity.Customer;
import com.example.demo.entity.SetMeal;
import com.example.demo.entity.Type;
import com.example.demo.service.SetMealService;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wicky
 * @since 2018-05-01
 */
@Controller
@RequestMapping("/setMeal")
public class SetMealController {
    @Autowired
   private SetMealService setMealService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/list")
    public String showSetMeal(Model model){
        GeneralController generalController=new GeneralController();

        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllSetMeal();
        returnAllType(model );
        return "setMeal/setMeal_list";

    }
    public void returnAllSetMeal(){
        Map map=new HashMap();
        java.util.List<SetMeal> setMeals= setMealService.selectByMap(map);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        session.setAttribute("setMeals",setMeals);

    }

    public Model returnAllType(Model model) {
        Map map = new HashMap();
        List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types", types);
        return model;

    }
}


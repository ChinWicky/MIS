package com.example.demo.web;


import com.example.demo.entity.SetMeal;
import com.example.demo.entity.SetMealSalesDetail;
import com.example.demo.entity.Type;
import com.example.demo.service.SetMealSalesDetailService;
import com.example.demo.service.SetMealSalesService;
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
@RequestMapping("/setMealDetail")
public class SetMealSalesDetailController {

    @Autowired
    private SetMealSalesDetailService setMealSalesDetailService;
    @Autowired
    private TypeService typeService;


    @RequestMapping("/list")
    public String showSetMealDetail(Model model,int setMealId ){
        GeneralController generalController=new GeneralController();

        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllSetMealDetail(setMealId);
        returnAllType(model);
        return "setMeal/setMealDetail_list";

    }
    public void returnAllSetMealDetail(int setMealId ){
       // Map map=new HashMap();
        //map.put("set_meal_id",setMealId);
       // java.util.List<SetMealSalesDetail> setMealDetails= setMealSalesDetailService.selectByMap(map);
        java.util.List<SetMealSalesDetail> setMealDetails= setMealSalesDetailService.findSetMeal(setMealId);
        System.out.println("size"+setMealDetails.size());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        session.setAttribute("setMealDetails",setMealDetails);
        session.setAttribute("setMealId",setMealId);

    }
    public Model returnAllType(Model model) {
        Map map = new HashMap();
        List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types", types);
        return model;

    }
}


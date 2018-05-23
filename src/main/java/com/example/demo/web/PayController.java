package com.example.demo.web;


import com.example.demo.entity.Pay;
import com.example.demo.entity.SevProject;
import com.example.demo.entity.SevSales;
import com.example.demo.entity.Type;
import com.example.demo.model.Cart;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
@RequestMapping("/transactionPay")
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private SevProjectService sevProjectService;
    @Autowired
    private SevSalesService sevSalesService;

    //支付
    @RequestMapping("/pay")
    public String pay(HttpServletRequest request, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        int sevSalesId = Integer.parseInt(request.getParameter("orderId"));
        SevSales sevSales= sevSalesService.selectById(sevSalesId);
        try {
            cart.setPay(sevSales);//更新次卡次数，用户消费总价
            sevSales.setState("已支付");
            sevSalesService.updateAllColumnById(sevSales);
            Pay pay=new Pay();
            Date time=new Date();
            pay.setPaymentTime(time);
            pay.setSalesId(sevSales.getSalesId());
            pay.setPayPrice(sevSales.getTotalPrice());
            payService.insert(pay);
        }catch(Exception e){
           // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("支付失败");
        }
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/sev_sales_list";
    }

    public Model returnAllType(Model model){
        Map map=new HashMap();
        List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        return model;

    }

    //通用方法查所有服务
    public Model returnAllSevPro(Model model){
        Map map=new HashMap();
        List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        model.addAttribute("sev_projects",sev_projects);
        return model;

    }
}


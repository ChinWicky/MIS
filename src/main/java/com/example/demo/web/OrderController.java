package com.example.demo.web;

import com.example.demo.entity.*;
import com.example.demo.model.Cart;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @since 2018-05-03
 */
@Controller
@Transactional
@RequestMapping("/transaction")
public class OrderController extends GlobalMethodSecurityConfiguration {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SevSalesService sevSalesService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private SevProjectService sevProjectService;
    @Autowired
    private SevSalesDetailsService sevSalesDetailsService;
    @Autowired
    private SecondaryCardService secondaryCardDetailsService;
    @Autowired
    private  PayService payService;
    @Autowired
    private WaiterService waiterService;


    @RequestMapping("/start")
    public  String startTransaction(HttpServletRequest request,HttpSession session,Model model){
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String phone = request.getParameter("phone");
        Map map=new HashMap();
        List<Waiter> waiters = waiterService.selectByMap(map);
        session.setAttribute("waiters",waiters);
//        map.put("phone",phone);
//        List<Customer> customers = customerService.selectByMap(map);
//        if(customers.size()==0){
//            return "500";
//        }
        Customer customer = customerService.findById(customerId); //不考虑相同号码
        if(customer==null)
            return "500";
        session.setAttribute("customer",customer);

        Cart cart = new Cart(sevProjectService,secondaryCardDetailsService,sevSalesDetailsService,customerService);
        //session服务员
        session.setAttribute("cart",cart);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/sevorderlist";
    }


    @RequestMapping("/toPhone")
    public String toPhone(HttpServletRequest request,Model model){



        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/add_phone";

    }

    @RequestMapping("/toOrder")
    public String toOrder(HttpSession session,Model model){
        Map map = new HashMap();
        List<SevSales> sevSales = sevSalesService.selectByMap(map);
        session.setAttribute("sevSales",sevSales);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/order_list";

    }

    @RequestMapping("/toChart")
    @ResponseBody
    public Map toChart(HttpSession session,Model model){
        Map map = new HashMap();
        List<SevSalesDetails> sevSales = sevSalesDetailsService.findAll();
        map = sevSalesDetailsService.getChart();
        //List<SevSales> sevSales = sevSalesService.selectByMap(map);
        return map ;
    }

    @RequestMapping("/Chart")
    public String getChart(HttpSession session,Model model){

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "chart/highChart" ;
    }


    @RequestMapping("/addToCart")
    public String addToCart(HttpSession session,SevSalesDetails sevSalesDetails,Model model,int waiterId){
        Cart cart =(Cart)session.getAttribute("cart");
        if(cart==null)
            return "500";
        sevSalesDetails.setWaiterId(waiterId);
        sevSalesDetails.setWaiter(waiterService.selectById(sevSalesDetails.getWaiterId()));
        cart.addSevProject(sevSalesDetails);
       // cart.getCostPrice();
        session.setAttribute("cart",cart);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/sevorderlist";
    }

    @RequestMapping("/deleteFromCart")
    public String deleteFromCart(HttpSession session, SevSalesDetails sevSalesDetails,Model model){
        Cart cart =(Cart)session.getAttribute("cart");
        if(cart==null)
            return "500";
        cart.deleteItem(sevSalesDetails );
        session.setAttribute("cart",cart);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/sevorderlist";
    }

    //点击确定呢时设置支付方式
    @RequestMapping("/SetPayment")
    public String SetSecondaryCard(HttpServletRequest request,Model model,SevSalesDetails sevSalesDetails,HttpSession session){
        Cart cart =(Cart)session.getAttribute("cart");

        String select=request.getParameter("paymentMethod");
        sevSalesDetails.setPaymentMethod(select);
        int waiterId = Integer.parseInt(request.getParameter("waiterId"));
        sevSalesDetails.setWaiter(waiterService.selectById(waiterId));
      //  System.out.println("次卡支付"+sevSalesDetails.getPaymentMethod());
        cart.updateCart(sevSalesDetails);//如果是次卡支付
        session.setAttribute("cart",cart);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/sevorderlist";

    }

    //支付
    @RequestMapping("/pay")
    public String pay(HttpServletRequest request, HttpSession session, Model model) {
        Cart cart = new Cart(sevProjectService,secondaryCardDetailsService,sevSalesDetailsService,customerService);
        Customer customer=(Customer)session.getAttribute("customer");
        int sevSalesId;
         sevSalesId = Integer.parseInt(request.getParameter("orderId"));
         SevSales sevSales= sevSalesService.selectById(sevSalesId);
      try {
            cart.setPay(sevSales);//更新次卡次数，用户消费总价
            sevSales.setState("已支付");
          if(customer.getHistoryTotalPrice()>=2500&&customer.getCustomerRoleId()==2)
          {
              customer.setCustomerRoleId(3);//高级会员
              this.customerService.updateById(customer);
          }
            Pay pay=new Pay();
            pay.setPaymentTime(new Date());
            pay.setSalesId(sevSales.getSalesId());
            pay.setPayPrice(sevSales.getTotalPrice());
            payService.insert(pay);
            sevSalesService.updateAllColumnById(sevSales);
            session.setAttribute("sevSales",sevSalesService.selectByMap(new HashMap()));
     }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("支付失败");
     }
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/order_list";
    }

    //提交订单
    @RequestMapping("/placeOrder")
    public String placeOrder(HttpServletRequest request,HttpSession session, Model model){
        Cart cart = (Cart)session.getAttribute("cart");
        Customer customer = (Customer)session.getAttribute("customer");
        Date time=new Date();
        SevSales sevSales = new SevSales();
        if(cart==null){
            GeneralController generalController=new GeneralController();
            //得到用户
            model=generalController.returnUser(model);
            //显示所有种类
            model= this.returnAllType(model);
            //显示所有服务
            model= this.returnAllSevPro(model);
            return "sale/order_list";
        }

       try {
            sevSales.setCustomerId(customer.getCustomerId());
            sevSales.setTotalPrice(cart.getPaymentPrice());
            sevSales.setSalesTime(time);
            sevSales.setState("未支付");
            sevSalesService.insert(sevSales);
            cart.setItem(sevSales);//将消费明细持久到数据库
            session.setAttribute("sevSales",sevSalesService.selectByMap(new HashMap()));
            session.removeAttribute("cart");
       }catch(Exception e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           System.out.println("添加订单失败");
       }
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "sale/order_list";
    }

    //删除订单
    @RequestMapping("/deleteOrder")
    public String deleteOrder(HttpServletRequest request, HttpSession session,Model model){
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Map map = new HashMap();
        map.put("sales_id",orderId);
        try{
        sevSalesDetailsService.deleteByMap(map);
        sevSalesService.deleteById(orderId);
         session.setAttribute("sevSales",sevSalesService.selectByMap(new HashMap()));
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("删除订单失败");
        }

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);

        return "sale/order_list";
    }

    @RequestMapping("/Select")
    public String SelectByName(HttpServletRequest request,Model model){

        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        String proName=request.getParameter("proName");
        java.util.List<SevProject> sev_projects = sevProjectService.findByName(proName);
        //返回列表
        model.addAttribute("sev_projects",sev_projects);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        this.returnAllType(model);
        return "sale/sevorderlist";
    }

    @RequestMapping("/showOrderDetail")
    public String showOrderDetail(HttpServletRequest request,Model model){
        int orderId= Integer.parseInt(request.getParameter("orderId"));
        List<SevSalesDetails> sevSalesDetailsList = sevSalesDetailsService.findSalesId(orderId);
        model.addAttribute("sevSalesDetails",sevSalesDetailsList);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        this.returnAllType(model);
        return "sale/order_detail";
    }


    public Model returnAllType(Model model){
        Map map=new HashMap();
        List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        return model;

    }

    //通用方法查所有服务
    public Model returnAllSevPro(Model model){
        List<SevProject> sev_projects = sevProjectService.findAll();
        model.addAttribute("sev_projects",sev_projects);
        return model;

    }


}


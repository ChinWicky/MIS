package com.example.demo.web;

import com.example.demo.entity.Customer;
import com.example.demo.entity.SecondaryCard;
import com.example.demo.entity.Type;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/customer")
public class CustomerController extends GlobalMethodSecurityConfiguration {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private SecondaryCardService secondaryCardService;


    @RequestMapping("/add")
    public String addCustomer(Model model, Customer customer) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        double i = 0;
        //临时
       // customer.setBirth(new Date());
        customer.setHistoryTotalPrice(i);
        customer.setCustomerRoleId(2);//初级会员
        this.customerService.insert(customer);

//        Map map = new HashMap();
//        map.put("phone", customer.getPhone());
//        Customer customer1 = (Customer) customerService.selectByMap(map).get(0);
        System.out.println(customer.getCustomerId());
        session.setAttribute("customer", customer);
        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllCustomer();
        returnAllType(model);
        return "customer/customer_list";

    }

    @RequestMapping("/toAdd")
    public String ToaddCustomer(Model model) {
        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        returnAllType(model);
        //显示所有种类
        //this.returnAllCustomer();
        return "customer/add_customer";

    }


    @RequestMapping("/delete")
    public String deleteCustomer(HttpServletRequest request, Model model) {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        this.customerService.deleteById(customerId);
        HttpSession session = request.getSession();
        session.setAttribute("customers",customerService.findAll());


        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllCustomer();
        returnAllType(model);
        return "customer/customer_list";
    }


    //修改成功之后返回列表
    @RequestMapping("/edit")
    public String editCustomer(Model model,HttpServletRequest request) {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String phone1=request.getParameter("phone");
        customer.setPhone(phone1);

        System.out.println("shoujihao"+phone1);
        System.out.println("id"+customer.getCustomerId());

        customerService.updateById(customer);
        session.setAttribute("customer", customer);
        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllCustomer();
        returnAllType(model);
        return "customer/customer_info";
    }

    //修改用户信息
    @RequestMapping("/toEdit")
    public String toEditCustomer(HttpServletRequest request,Model model,HttpSession session) {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = this.customerService.findById(customerId);
        session.setAttribute("customer",customer);

        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllCustomer();
        returnAllType(model);
        return "customer/edit_customer";

    }

    @RequestMapping("/SelectByPhone")
    public String SelectByPhone(Model model,HttpSession session) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String phone = request.getParameter("phone");
        List<Customer> customers = customerService.findPhone(phone);

        session.setAttribute("customers", customers);
        GeneralController generalController = new GeneralController();
        //得到用户
        model = generalController.returnUser(model);
        //显示所有种类
        returnAllType(model);
        return "customer/customer_list";
    }

    @RequestMapping("/SelectById")
    public String SelectById(Model model,HttpSession session) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int customerId = Integer.parseInt(request.getParameter("customerId"));
//        List<Customer> customers = customerService.findPhone(phone);
//        Customer customer = customers.get(0);
        Customer customer = customerService.findById(customerId);
        session.setAttribute("customer", customer);
        GeneralController generalController = new GeneralController();
        //得到用户
        model = generalController.returnUser(model);
        //显示所有种类
        returnAllType(model);
        List<SecondaryCard> secondaryCards= secondaryCardService.findSecondaryCardById(customer.getCustomerId());
        session.setAttribute("secondaryCards",secondaryCards);
        return "customer/customer_info";
    }

    @RequestMapping("/Select")
    public String SelectByName(Model model,HttpSession session) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String phone = request.getParameter("phone");
       // Map map = new HashMap();
       // map.put("phone",phone);
       // List<Customer> customers = customerService.selectByMap(map);
        List<Customer> customers = customerService.findPhone(phone);
        Customer customer = customers.get(0);

        session.setAttribute("customer", customer);
        GeneralController generalController = new GeneralController();
        //得到用户
        model = generalController.returnUser(model);
        //显示所有种类
        returnAllType(model);
        //Map map1=new HashMap();
        //map1.put("secondary_card_id",customer.getCustomerId());
       // List<SecondaryCard> secondaryCards=secondaryCardService.selectByMap(map1);
        List<SecondaryCard> secondaryCards= secondaryCardService.findSecondaryCardById(customer.getCustomerId());
        session.setAttribute("secondaryCards",secondaryCards);
        return "customer/customer_info";
    }

    @RequestMapping("/toSelect")
    public String ToaddPhone(Model model) {
        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        returnAllType(model);
        //显示所有种类
        return "customer/add_phone";

    }

//访问所有customer列表

    @RequestMapping("/list")
    public String showCustomer(Model model) {
        GeneralController generalController = new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类
        this.returnAllCustomer();
        returnAllType(model);
        return "customer/customer_list";

    }

    public void returnAllCustomer() {
        java.util.List<Customer> customers = customerService.findAll();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("customers", customers);

    }


    public Model returnAllType(Model model) {
        Map map = new HashMap();
        List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types", types);
        return model;

    }

}
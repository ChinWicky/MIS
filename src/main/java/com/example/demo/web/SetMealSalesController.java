package com.example.demo.web;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
@RequestMapping("/setMealSales")
public class SetMealSalesController {
    @Autowired
    private SetMealSalesService setMealSalesService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SetMealSalesDetailService setMealDetailService;
    @Autowired
    private SecondaryCardService secondaryCardService;
    @Autowired
    private SetMealService setMealService;

    @RequestMapping("/add")
    public String addSetMeal(Model model,int setMealId){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       HttpSession session=request.getSession();
       Customer customer=(Customer)session.getAttribute("customer");
        Date time=new Date();
        SetMealSales setMealSales=new SetMealSales();
        setMealSales.setCustomerId(customer.getCustomerId());
        setMealSales.setSetMealId(setMealId);
        setMealSales.setSetMealSalesTime(time);
        setMealSalesService.insert(setMealSales);


        //更新历史消费总价
        SetMeal  setMeal= setMealService.selectById(setMealSales.getSetMealId());
        double price=customer.getHistoryTotalPrice()+setMealService.selectById(setMealSales.getSetMealId()).getSetMealPrice();
        customer.setHistoryTotalPrice(price);


        //更新会员等级
        if(customer.getHistoryTotalPrice()>2500)
            customer.setCustomerRoleId(3);//高级会员
        this.customerService.updateById(customer);

        //更新会员卡次数
        SetMealSalesDetail setMealDetail;
        SecondaryCard secondaryCard;
        SecondaryCard secondaryCard1;
//        Map map=new HashMap();
//        map.put("set_meal_id",setMealId);
//       List<SetMealSalesDetail> setMealDetails=setMealDetailService.selectByMap(map);
        List<SetMealSalesDetail> setMealDetails= setMealDetailService.findSetMeal(setMealId);
//        Map map1=new HashMap();
//        map1.put("secondary_card_id",customer.getCustomerId());
//        List<SecondaryCard> secondaryCards=secondaryCardService.selectByMap(map1);
        List<SecondaryCard> secondaryCards = secondaryCardService.findSecondaryCardById(customer.getCustomerId());
        int queck;//判断有无相等
        for (int i = 0; i < setMealDetails.size(); i++) {
            queck=-1;
            setMealDetail = setMealDetails.get(i);
            for (int j = 0; j < secondaryCards.size(); j++) {
                secondaryCard = secondaryCards.get(j);
               // secondaryCard1 = secondaryCards.get(j);
                if ((setMealDetail.getProId() == secondaryCard.getProId())) {
                    queck=j;
                    System.out.println("count"+secondaryCard.getCount());
                    System.out.println("proid"+secondaryCard.getProId());
                    System.out.println("customerid"+secondaryCard.getSecondaryCardId());
                    break;
                }
            }
            if(queck==-1){
                secondaryCard=new  SecondaryCard();
                secondaryCard.setProId(setMealDetail.getProId());
                secondaryCard.setCount(setMealDetail.getTotalCount());
                secondaryCard.setSecondaryCardId(customer.getCustomerId());
                secondaryCardService.insert(secondaryCard);
            }
            else{
                secondaryCard1 = secondaryCards.get(queck);
                secondaryCard1.setCount(secondaryCard1.getCount() + setMealDetail.getTotalCount());
                secondaryCard1.setProId(secondaryCard1.getProId());
                secondaryCardService.updateById(secondaryCard1);
            }
        }
        //secondaryCards=secondaryCardService.selectByMap(map1);
        secondaryCards = secondaryCardService.findSecondaryCardById(customer.getCustomerId());
        session.setAttribute("secondaryCards",secondaryCards);


        GeneralController generalController=new GeneralController();
        //得到用户
        generalController.returnUser(model);
        //显示所有种类

        return "customer/customer_info";

    }


}


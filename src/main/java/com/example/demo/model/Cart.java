package com.example.demo.model;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private SevProjectService sevProjectService;
	private SecondaryCardService secondaryCardDetailsService;
	private SevSalesDetailsService sevSalesDetailsService ;
	private CustomerService customerService;


	private List<SevSalesDetails> items;
	private SevSalesDetails item;
	private SecondaryCard secondaryCard;




	public Cart(SevProjectService sevProjectService,SecondaryCardService secondaryCardDetailsService,SevSalesDetailsService sevSalesDetailsService,CustomerService customerService) {
		this.sevProjectService =sevProjectService;
		this.secondaryCardDetailsService=secondaryCardDetailsService;
		this.sevSalesDetailsService=sevSalesDetailsService;
		this.customerService=customerService;
		if (items == null)
			items = new ArrayList<>();
	}


	public void addSevProject(SevSalesDetails sevSalesDetails) {
	//	item = sevSalesDetails;
		//设置payment有无次卡可用，有payment=“1”,无=“0”
		System.out.println(sevSalesDetails.getProId());
		SevSalesDetails item1 = new SevSalesDetails();
		//判断次卡相应次数是否大于购买的服务次数
		int j = -1;//等于其他值的话就是已经有了相同的服务员，相同的proid
		if (items.size() <= 0) {
			System.out.println("items为0");
			item = this.setSecondaryCardDetailsCount(sevSalesDetails);
			items.add(item);
		} else {
			System.out.println("items不为空");
			for (int i = 0; i < items.size(); i++) {
				item1 = items.get(i);
				//判断是否有proid以及waiter主键在里面
				if ((item1.getProId() == sevSalesDetails.getProId()) && (item1.getWaiterId() == sevSalesDetails.getWaiterId())) {
					j = i;
					break;
				}
			}

			if (j != -1) {//有相同的服务员，相同的proid
				System.out.println("有相同");
				sevSalesDetails.setQuantity(sevSalesDetails.getQuantity() + item1.getQuantity());//购物车中的item
				item = this.setSecondaryCardDetailsCount(sevSalesDetails);
				items.set(j, item);
			} else {
				item = this.setSecondaryCardDetailsCount(sevSalesDetails);
				items.add(item);
			}

		}

	}

	public void updateCart(SevSalesDetails sevSalesDetails) {

		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			//判断是否有proid以及waiter主键在里面
			if ((item.getProId() == sevSalesDetails.getProId()) && (item.getWaiterId() ==sevSalesDetails.getWaiterId() )) {
				item=this.setSecondaryCardDetailsCount(sevSalesDetails);
				items.set(i, item);
				break;

			}
		}
	}

	//原价
	public double getOriginalPrice() {
		double totalPrice = 0;
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				double unit_price = sevProjectService.selectById(item.getProId()).getPrice();
				totalPrice = item.getQuantity() * unit_price + totalPrice;
			}

		}
		return totalPrice;
	}

	//set销售折后单价，以及支付方式
	public double setSaleCost() {
		double totalPrice = 0;
		double unit_price;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session=request.getSession();
		Customer customer=(Customer)session.getAttribute("customer");
		int roleId = customer.getCustomerRoleId();
		String card = "次卡支付";
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				if (item.getPaymentMethod().equals(card))
					roleId = 4;
				if (roleId == 1)//路人
				{
					unit_price = sevProjectService.selectById(item.getProId()).getPrice();
					item.setPaymentMethod("现金支付");
				}
				else if (roleId == 2)//初级会员
				{
					unit_price = sevProjectService.selectById(item.getProId()).getPriPrice();
					item.setPaymentMethod("现金支付");
				}
				else if (roleId == 3)//高级会员
				{
					unit_price = sevProjectService.selectById(item.getProId()).getHigPrice();
					item.setPaymentMethod("现金支付");
					}
				else//次卡支付
					unit_price = 0;
				//设置单价，若次卡支付设为0
				item.setPostDiscountPrice(unit_price);
				items.set(i,item);
				System.out.println("支付单价"+item.getPostDiscountPrice());
				//计算总价
				totalPrice = item.getQuantity() * unit_price + totalPrice;

			}
		}
		return totalPrice;

	}

	//折后价
	public double getCostPrice() {
		double totalPrice = 0;
		double unit_price;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
	//	Customer customer = (Customer)request.getSession().getAttribute("customer");
		Customer customer=(Customer)session.getAttribute("customer");
		int roleId = customer.getCustomerRoleId();
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				System.out.println(item.getProId());

				//判断单价
				if (roleId == 1)
					unit_price = sevProjectService.selectById(item.getProId()).getPrice();
				else if (roleId == 2)
					unit_price = sevProjectService.selectById(item.getProId()).getPriPrice();
				else
					unit_price = sevProjectService.selectById(item.getProId()).getHigPrice();
				//计算总价
				totalPrice = item.getQuantity() * unit_price + totalPrice;

			}
		}

		return totalPrice;
	}




	//得到支付价
	public double getPaymentPrice() {
		double totalPrice = 0;
		double unit_price;
		String card = "次卡支付";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session=request.getSession();
		Customer customer=(Customer)session.getAttribute("customer");
		int roleId = customer.getCustomerRoleId();
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);

				//判断单价
				if (item.getPaymentMethod().equals(card))
					roleId = 4;
				if (roleId == 1)//路人
				{
					unit_price = sevProjectService.selectById(item.getProId()).getPrice();
				}
				else if (roleId == 2)//初级会员
				{
					unit_price = sevProjectService.selectById(item.getProId()).getPriPrice();

				}
				else if (roleId == 3)//高级会员
				{
					unit_price = sevProjectService.selectById(item.getProId()).getHigPrice();
				}
				else//次卡支付
					unit_price = 0;
				//设置单价，若次卡支付设为0

				//计算总价
				totalPrice = item.getQuantity() * unit_price + totalPrice;

			}
		}

		return totalPrice;
	}

	//删除购物车的项目
	public Boolean deleteItem(SevSalesDetails sevSalesDetails) {
		int j = 0;
		Boolean k = false;
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				//判断是否有proid以及waiter主键在里面
				if ((item.getProId() == sevSalesDetails.getProId()) && (item.getWaiterId() == sevSalesDetails.getWaiterId())) {
					j = 1;
					break;
				}
			}
			if (j ==1) {//有相同的服务员，相同的proid
				items.remove(item);
				k = true;

			}
		}
		return k;
	}


	//更改选择现金支付时
	public void setItemPayment(SevSalesDetails sevSalesDetails) {
		System.out.println("现金支付"+item.getPaymentMethod());
		item=sevSalesDetails;
		SevSalesDetails item1=new SevSalesDetails();
		//判断次卡相应次数是否大于购买的服务次数
		int j = -1;//等于其他值的话就是已经有了相同的服务员，相同的proid

		for (int i = 0; i < items.size(); i++) {
			item1 = items.get(i);
			//判断是否有proid以及waiter主键在里面
			if ((item1.getProId() == item.getProId()) && (item1.getWaiterId() == item.getWaiterId())) {
				j = i;
				break;
			}
		}
		if (j != -1) {//有相同的服务员，相同的proid
			items.set(j, item);
		}


	}

	//将每一个sevSalesDetails持久化到数据库
	public void setItem(SevSales sevSales) {
		int SalesId=sevSales.getSalesId();
		for ( SevSalesDetails item:items) {
				//item=items.get(i);
				item.setSalesId(SalesId);
				System.out.println("销售id"+item.getSalesId());
				System.out.println("折后价"+item.getPostDiscountPrice());
				System.out.println("支付方式"+item.getPaymentMethod());
				System.out.println("数量"+item.getQuantity());
				System.out.println("服务编号"+item.getProId());
				System.out.println("服务元编号"+item.getWaiterId());
				sevSalesDetailsService.insert(item);
		}

	}

	//支付时调用的方法，更细用户次卡次数,以及用户历史总价
	public void setPay(SevSales sevSales) {
		//int SalesId = sevSales.getSalesId();
		//SevSalesDetails item= null;
		SecondaryCard  secondaryCard;
		double totalPrice=0;
		String card = "次卡支付";
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Customer customer = customerService.selectById(sevSales.getCustomerId());
		//Map map=new HashMap();
		//map.put("salesId",sevSales.getSalesId());
		List<SevSalesDetails> items=sevSalesDetailsService.findSalesId(sevSales.getSalesId());
        System.out.println("waiterName"+items.get(0).getWaiter().getWaiterName());
        System.out.println("waiterId"+items.get(0).getWaiterId());
        System.out.println("paymentMethod"+items.get(0).getPaymentMethod());
      //  System.out.println("客户Id"+customer.getCustomerId());
		Map map1=new HashMap();
		map1.put("secondary_card_id", customer.getCustomerId());
			for (SevSalesDetails item:items) {
				if (item.getPaymentMethod().equals(card)){
					map1.put("pro_id", item.getProId());
				    secondaryCard = (SecondaryCard) secondaryCardDetailsService.selectByMap(map1).get(0);
				    secondaryCard.setCount(secondaryCard.getCount() - item.getQuantity());
				    secondaryCardDetailsService.updateAllColumnById(secondaryCard);
				}
				//计算总价
				totalPrice = item.getQuantity() * item.getPostDiscountPrice() + totalPrice;
			}

		customer.setHistoryTotalPrice(customer.getHistoryTotalPrice() +  totalPrice);
		System.out.println("更新用户价格"+(customer.getHistoryTotalPrice() + totalPrice));
		System.out.println("客户Id"+customer.getCustomerId());
		customerService.updateById(customer);
	}

	//支付时调用的方法，更细用户次卡次数,以及用户历史总价
//	public void setPay(SevSales sevSales) {
//		int SalesId = sevSales.getSalesId();
//		String card = "次卡支付";
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		Customer customer = (Customer) session.getAttribute("customer");
//		Map map = new HashMap();
//		map.put("secondary_card_id", customer.getCustomerId());
//		if (items.size() != 0) {
//			for (int i = 0; i < items.size(); i++) {
//				item.setSalesId(SalesId);
//				int customerId = customer.getCustomerId();
//				if (item.getPaymentMethod().equals(card))
//					map.put("pro_id", item.getProId());
//				secondaryCard = (SecondaryCard) secondaryCardDetailsService.selectByMap(map).get(0);
//				secondaryCard.setCount(secondaryCard.getCount() - item.getQuantity());
//				secondaryCardDetailsService.updateAllColumnById(secondaryCard);
//			}
//		}
//		customer.setHistoryTotalPrice(customer.getHistoryTotalPrice() + this.getPaymentPrice());
//		System.out.println("更新用户价格"+(customer.getHistoryTotalPrice() + this.getPaymentPrice()));
//		System.out.println("客户Id"+customer.getCustomerId());
//		//customerService.updateById(customer);
//		customerService.updateAllColumnById(customer);
//	}

	public List<SevSalesDetails> getItems() {
		return items;
	}

	public void setItems(List<SevSalesDetails> items) {
		this.items = items;
	}

	//判断次卡是否大于服务项目的次数，大于默认支付方式是次卡，小于默认支付方式是现金
	public SevSalesDetails setSecondaryCardDetailsCount(SevSalesDetails sevSalesDetails) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		//Customer customer = (Customer) request.getSession().getAttribute("customer");
		Customer customer=(Customer)session.getAttribute("customer");
		int customerId = customer.getCustomerId();
		Map map = new HashMap();
		map.put("pro_id", sevSalesDetails.getProId());
		map.put("secondary_card_id", customerId);
		System.out.println("服务编号"+sevSalesDetails.getProId());
		System.out.println("顾客Id"+customerId);
		if( secondaryCardDetailsService.selectByMap(map).size()!=0)
		    secondaryCard = (SecondaryCard) secondaryCardDetailsService.selectByMap(map).get(0);
		else
			secondaryCard = null;
		if(secondaryCard!=null){
			System.out.println(secondaryCard.getCount()); //10
			System.out.println(sevSalesDetails.getQuantity()); //null
			System.out.println(sevSalesDetails.getProId()+","+sevSalesDetails.getWaiterId()); //1,null

			if (secondaryCard.getCount() >= sevSalesDetails.getQuantity()) {
				sevSalesDetails.setPaymentMethod("次卡支付");
			} else {
				sevSalesDetails.setPaymentMethod("现金支付");
			}}
		else {
			sevSalesDetails.setPaymentMethod("现金支付");
		}
		return sevSalesDetails;
	}

}
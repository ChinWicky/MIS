package com.example.demo.model;
import com.example.demo.entity.*;
import com.example.demo.service.*;
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
	private List<Item> items;
	private Item item;
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
		Item item1 = new Item();
		//判断次卡相应次数是否大于购买的服务次数
		int j = -1;//等于其他值的话就是已经有了相同的服务员，相同的proid
		if (items.size() <= 0) {
			System.out.println("items为0");
			item = this.setSecondaryCardDetailsCount(sevSalesDetails);
			String proName=sevProjectService.selectById(sevSalesDetails.getProId()).getProName();
			item.setProName(proName);
			item=this.setPostDiscount(item);
			items.add(item);
		} else {
			System.out.println("items不为空");
			for (int i = 0; i < items.size(); i++) {
				item1 = items.get(i);
				//判断是否有proid以及waiter主键在里面
				if ((item1.gettSevSalesDetails().getProId() == sevSalesDetails.getProId()) && (item1.gettSevSalesDetails().getWaiterId() == sevSalesDetails.getWaiterId())) {
					j = i;
					break;
				}
			}

			if (j != -1) {//有相同的服务员，相同的proid
				System.out.println("有相同");
				sevSalesDetails.setQuantity(sevSalesDetails.getQuantity() + item1.gettSevSalesDetails().getQuantity());//购物车中的item
				item = this.setSecondaryCardDetailsCount(sevSalesDetails);
				item=this.setPostDiscount(item);
				String proName=sevProjectService.selectById(sevSalesDetails.getProId()).getProName();
				item.setProName(proName);
				items.set(j, item);

			} else {
				item = this.setSecondaryCardDetailsCount(sevSalesDetails);
				item=this.setPostDiscount(item);
				String proName=sevProjectService.selectById(sevSalesDetails.getProId()).getProName();
				item.setProName(proName);
				items.add(item);
			}

		}

	}

	public void updateCart(SevSalesDetails sevSalesDetails) {

		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			//判断是否有proid以及waiter主键在里面
			if ((item.gettSevSalesDetails().getProId() == sevSalesDetails.getProId()) && (item.gettSevSalesDetails().getWaiterId() ==sevSalesDetails.getWaiterId() )) {
				item=this.setSecondaryCardDetailsCount(sevSalesDetails);
				item=this.setPostDiscount(item);
				String proName=sevProjectService.selectById(sevSalesDetails.getProId()).getProName();
				item.setProName(proName);
				items.set(i,item);
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
				double unit_price = sevProjectService.selectById(item.gettSevSalesDetails().getProId()).getPrice();
				totalPrice = item.gettSevSalesDetails().getQuantity() * unit_price + totalPrice;
			}

		}
		return totalPrice;
	}


	//得到支付价
	public double getPaymentPrice() {
	double totalPrice=0;
		if (items.size() != 0) {
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);

				//计算总价
				totalPrice = item.gettSevSalesDetails().getQuantity() * item.gettSevSalesDetails().getPostDiscountPrice() + totalPrice;

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
				if ((item.gettSevSalesDetails().getProId() == sevSalesDetails.getProId()) && (item.gettSevSalesDetails().getWaiterId() == sevSalesDetails.getWaiterId())) {
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


	//set折后单价
	public Item setPostDiscount(Item item) {
		double unit_price;
		String card = "次卡支付";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session=request.getSession();
		Customer customer=(Customer)session.getAttribute("customer");
		int roleId = customer.getCustomerRoleId();
		if (item.gettSevSalesDetails().getPaymentMethod().equals(card))
			roleId = 4;
		if (roleId == 1)//路人
		{
			unit_price = sevProjectService.selectById(item.gettSevSalesDetails().getProId()).getPrice();
		}
		else if (roleId == 2)//初级会员
		{
			unit_price = sevProjectService.selectById(item.gettSevSalesDetails().getProId()).getPriPrice();

		}
		else if (roleId == 3)//高级会员
		{
			unit_price = sevProjectService.selectById(item.gettSevSalesDetails().getProId()).getHigPrice();
		}
		else//次卡支付
			unit_price = 0;
		item.gettSevSalesDetails().setPostDiscountPrice(unit_price);
       return item;
	}



	//将每一个sevSalesDetails持久化到数据库
	public void setItem(SevSales sevSales) {
		int SalesId=sevSales.getSalesId();
		for (Item item:items) {
				//item=items.get(i);
				item.gettSevSalesDetails().setSalesId(SalesId);
				sevSalesDetailsService.insert(item.gettSevSalesDetails());
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
		if(customer.getHistoryTotalPrice()>=2500&&customer.getCustomerRoleId()==2)
		{
			customer.setCustomerRoleId(3);//高级会员
		}
		customer.setHistoryTotalPrice(customer.getHistoryTotalPrice() +  totalPrice);
//		System.out.println("更新用户价格"+(customer.getHistoryTotalPrice() + totalPrice));
//		System.out.println("客户Id"+customer.getCustomerId());
		customerService.updateById(customer);
	}



	//判断次卡是否大于服务项目的次数，大于默认支付方式是次卡，小于默认支付方式是现金
	public Item setSecondaryCardDetailsCount(SevSalesDetails sevSalesDetails) {
		Item item;
		String check;
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
				if(sevSalesDetails.getPaymentMethod()==null){
				sevSalesDetails.setPaymentMethod("次卡支付");}
				check="是";
			} else {
				if(sevSalesDetails.getPaymentMethod()==null){
				sevSalesDetails.setPaymentMethod("现金支付");}
				check="否";
			}}
		else {
			if(sevSalesDetails.getPaymentMethod()==null){
			sevSalesDetails.setPaymentMethod("现金支付");}
			check="否";
		}
		item=new Item(check,sevSalesDetails);
		return item;
	}

	public java.util.List<Item> getItems(){
		return items;
	}

}
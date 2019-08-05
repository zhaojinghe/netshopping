package com.school.controller.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.common.web.Pager;
import com.school.pojo.Order;
import com.school.pojo.OrderDetail;
import com.school.pojo.Product;
import com.school.service.order.OrderService;
import com.school.service.product.ProductService;

@Controller
public class OrderController {
	
	@Resource
	private OrderService orderService;
	@Resource
	private ProductService productService;
	
	 @RequestMapping("/orderList") 
	public String findOrders(@RequestParam(value="userId",required=false)Integer userId ,
			@RequestParam(required=true,defaultValue="1")Integer page ,
			Model model) {
	        
		   PageHelper.startPage(page, 5);
			
			List<Order>  orderList= orderService.findOrderInfos(userId);
		    for(Order order:orderList){
		    	
			  order.setOrderDetailList(orderService.findOrderDetailInfos(order.getId()));
		    }
		    Pager pages=new Pager();
			pages.setUrl("orderList");
			PageInfo<Order>  listpage=new PageInfo<>(orderList);
		    model.addAttribute("orderList", orderList);
		    model.addAttribute("pager", listpage);
		    model.addAttribute("pageurl", pages);
		 
		 return "backend/order/orderList";
	}
	 
	 @RequestMapping("/orderdetail") 
	public String findorderdetail(@RequestParam("orderId")Integer orderId ,
			
			Model model) {
	        
		   	List<OrderDetail>  orderDetailList= orderService.findOrderDetailInfos(orderId);
		   
		    model.addAttribute("orderDetailList", orderDetailList);
		 
		 return "backend/order/orderDetailList";
	}

}

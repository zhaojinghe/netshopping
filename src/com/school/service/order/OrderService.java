package com.school.service.order;

import java.util.List;

import com.school.pojo.Order;
import com.school.pojo.OrderDetail;
import com.school.pojo.User;
import com.school.util.ShoppingCart;

public interface OrderService {
	/**
	 * 
	 * 根据查询条件，分页显示订单信息列表（返回类型：List<Order>，参数：当前页码、页码容量，用户id）。
	 * @param userid
	 * @return
	 */
	List<Order>  findOrderInfos(Integer userid);
	/**
	 * 
	 * 根据订单id查询订单明细列表（返回类型：List<OrderDetail>，参数：订单id）。
	 * @param orderid
	 * @return
	 */
	List<OrderDetail> findOrderDetailInfos(int orderid);
	
	/**
	 * 结算订单（返回类型：Order对象，参数：ShoppingCart对象、User对象、收货地址）。
	 * @param cart
	 * @param user
	 * @param address
	 * @return
	 */
	Order payShoppingCart(ShoppingCart cart ,User user,String address);

}

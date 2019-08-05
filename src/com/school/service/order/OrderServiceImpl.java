package com.school.service.order;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.school.dao.OrderDetailMapper;
import com.school.dao.OrderMapper;
import com.school.dao.ProductMapper;
import com.school.pojo.Order;
import com.school.pojo.OrderDetail;
import com.school.pojo.OrderDetailExample;
import com.school.pojo.OrderExample;
import com.school.pojo.Product;
import com.school.pojo.ProductExample;
import com.school.pojo.User;
import com.school.util.ShoppingCart;
import com.school.util.ShoppingCartItem;
import com.school.util.StringUtils;
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	 
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private OrderDetailMapper  orderDetailMapper;
	@Resource
	private ProductMapper productMapper;
	
	
	@Override
	public List<Order> findOrderInfos(Integer userid) {
		// TODO Auto-generated method stub
		
		OrderExample  example =new OrderExample();
		if(userid!=null) {
		   example.createCriteria().andUseridEqualTo(userid);
		
		}else {
			
			example=null;
		}
		return orderMapper.selectByExample(example);
	}

	@Override
	public List<OrderDetail> findOrderDetailInfos(int orderid) {
		// TODO Auto-generated method stub
		
		OrderDetailExample  example =new OrderDetailExample();
		
		example.createCriteria().andOrderidEqualTo(orderid);
		
		List<OrderDetail>  orderDetailList= orderDetailMapper.selectByExample(example);
		
	    for(OrderDetail orderDetail:orderDetailList){
	    	
	    	orderDetail.setProduct(productMapper.selectByPrimaryKey(orderDetail.getProductid()));
	    }
		return orderDetailList;
	}

	/**
     * 结算购物车物品包含以下步骤：
     * 1.生成订单
     * 2.生成订单明细
     * 3.更新商品表，减库存
     * 注意加入事物的控制
     */
	@Override
	public Order payShoppingCart(ShoppingCart cart, User user, String address) {
		// TODO Auto-generated method stub
		
		
		Order order =new Order();
		 //增加订单
	     order.setLoginname(user.getLoginname());
	     order.setCost(cart.getTotalCost());
	     order.setCreatetime(new Date());
	     order.setSerialnumber(StringUtils.randomUUID());
	     order.setUseraddress(address);
	     order.setUserid(user.getId());
	     
	     orderMapper.insertSelective(order);
	   //增加订单对应的明细信息
	     for(ShoppingCartItem item:cart.getItems()) {
	    	 
	    	 
	    	 OrderDetail  orderDetail=new OrderDetail();
	    	 
	    	 orderDetail.setCost(item.getCost());
	    	 orderDetail.setQuantity(item.getQuantity());
	    	 orderDetail.setOrderid(order.getId());
	    	 orderDetail.setProductid(item.getProduct().getId());
	    	 
	    	 orderDetailMapper.insertSelective(orderDetail);
	    	 
	    	   //更新商品表的库存
	    	 
	    	 Product product=new Product();
	    	 
	    	product.setId(item.getProduct().getId());
	    	product.setStock(item.getQuantity());
	    	 productMapper.updateByPrimaryKeySelective(product);
	    	 
	     }
		
		return order;
	}
	
	

}

package com.school.service.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.util.ShoppingCart;
import com.school.util.ShoppingCartItem;

@Service
@Transactional
public class CartServiceImpl  implements CartService{
  
	/**
	 * 修改购物车的数量
	 *
	 */
	@Override
	public ShoppingCart modifyCart(Integer productId, Integer quantity, ShoppingCart cart) {
		// TODO Auto-generated method stub
		
		
		for(ShoppingCartItem item:cart.getItems()) {
			
			if(item.getProduct().getId()==productId) {
				
			
			  if(quantity==0 || quantity<0) {
				  
				  cart.getItems().remove(item);
				  
				  
			  }else {
				  
				  item.setQuantity(quantity);
			  }
			
			}
		}
		
		this.calulate(cart);
		return cart;
	}
   /**
    * 
    * 核算购物车的金额
    */
	@Override
	public ShoppingCart calulate(ShoppingCart cart) {
		// TODO Auto-generated method stub
		
		double sum=0.0;
		
		for(ShoppingCartItem item:cart.getItems()) {
			
			sum+=item.getQuantity()*item.getProduct().getPrice();
			cart.setSum(sum);
			
		}
		return cart;
	}

	
	
}

package com.school.service.order;

import com.school.util.ShoppingCart;

public interface CartService {
	
	
	public ShoppingCart modifyCart(Integer productId,Integer quantity,
			ShoppingCart cart) ;
	
	
	public ShoppingCart calulate(ShoppingCart cart);

}

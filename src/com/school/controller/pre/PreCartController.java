package com.school.controller.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.exception.BizException;
import com.school.common.utils.FastJsonUtil;
import com.school.common.web.BaseController;
import com.school.pojo.Order;
import com.school.pojo.Product;
import com.school.pojo.User;
import com.school.pojo.UserAddress;
import com.school.service.address.UserAddressService;
import com.school.service.category.CategoryService;
import com.school.service.order.CartService;
import com.school.service.order.OrderService;
import com.school.service.product.ProductService;
import com.school.service.user.UserService;
import com.school.util.Constants;
import com.school.util.EmptyUtils;
import com.school.util.ProductCategoryVo;
import com.school.util.ReturnResult;
import com.school.util.ShoppingCart;
import com.school.util.ShoppingCartItem;

@Controller
public class PreCartController extends BaseController{
	
	@Resource
	private UserService userService;
	@Resource
	private ProductService productService;
	
	@Resource
	private UserAddressService uerAddressService;
	@Resource
	private OrderService orderService;
	
	@Resource
	private CartService cartService;
	
	@Resource
	private CategoryService categoryService;
	
  
    
	@RequestMapping(value="/addcart",produces=JSON_PRODUCE_TYPE)
	@ResponseBody
	public String addCart(@RequestParam("productid")Integer productid,
			@RequestParam(value="quantity",defaultValue="1")Integer quantity,
			HttpServletRequest request,Model model) throws BizException  {
		 //查询出商品
		Product product=productService.findById(productid);
		
		if(product.getStock()<quantity) {
			throw new BizException(FastJsonUtil.getResponseJson(2000, "商品数量不足！",null));
		
		}
		ShoppingCart cart=new ShoppingCart();
		 //获取购物车
		try {
			 cart=this.getCartFromSession(request);
			
			
		    //往购物车放置商品
	        
			cart.addItem(product, quantity);
			
			cart.setSum((EmptyUtils.isEmpty(cart.getSum())?0.0:cart.getSum())+(product.getPrice()*quantity*1.0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return dealException(2000, "系统异常", e);
		}
		
		
		return  dealQueryResult(cart, cart);
	}
	
    /**
     * 刷新购物车
     *
     * @param request
     * @param response
     * @return
     */
	 @RequestMapping("/refreshcart")
    public String refreshCart(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ShoppingCart cart = getCartFromSession(request);
        cart = cartService.calulate(cart);
        session.setAttribute("cart", cart);//全部的商品
        return "common/pre/searchBar";
    }
	
	  /**
	     * 从session中获取购物车
	     *
	     * @param request
	     * @return
	     */
	    private ShoppingCart getCartFromSession(HttpServletRequest request) throws Exception {
	        HttpSession session = request.getSession();
	        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new ShoppingCart();
	            session.setAttribute("cart", cart);
	        }
	        return cart;
	    }
	    
	 /**
     * 修改购物车信息
     *
     * @param request
     * @return
     */
	@RequestMapping("/modifycart")
    public ReturnResult modifyCart(@RequestParam("productid")Integer productid,
			@RequestParam(value="quantity",defaultValue="1")Integer quantity,
			HttpServletRequest request) throws Exception {
    	ReturnResult result = new ReturnResult();
    	HttpSession session = request.getSession();
        
        ShoppingCart cart = getCartFromSession(request);
    	Product product=productService.findById(productid);
    	
    		if(quantity>product.getStock()){
    			return result.returnFail("商品数量不足");
        	}
    	
        cart = cartService.modifyCart(productid, quantity, cart);
        session.setAttribute("cart", cart);//全部的商品
        return result.returnSuccess();
    }
	
	 /**
     * 生成订单
     *
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value="/settlement3")
    public Object settlement3(@RequestParam("addressId")Integer addressId,
    		@RequestParam("newAddress")String newAddress,
    		@RequestParam("newRemark")String newRemark,
    		HttpServletRequest request,
    		Model model) throws Exception {
    	
        ShoppingCart cart = getCartFromSession(request);
        cart = cartService.calulate(cart);
        User user = getUserFromSession(request);
       
        ReturnResult result=checkCart(request);
        if(result.getStatus()==Constants.ReturnResult.FAIL){
        	return result;
        }
        //新增地址
     
        UserAddress userAddress=new UserAddress();
        if(addressId.equals("-1")){
            userAddress.setRemark(newRemark);
            userAddress.setAddress(newAddress);
            userAddress.setUserid(user.getId());
            userAddress.setCreatetime(new Date());
           uerAddressService.addUserAddress(userAddress);
        }else{
        	userAddress=uerAddressService.findAddressInfos(0, addressId).get(0);
        }
        
        //生成订单
        Order order = orderService.payShoppingCart(cart,user,userAddress.getAddress());
        clearCart(request);
        model.addAttribute("currentOrder", order);
     /*   List<Order>  resultorder=new ArrayList<>();
        resultorder.add(order);*/
       // return dealQueryResult(resultorder, resultorder);
        return "pre/settlement/settlement3";
    }

	
    /**
     * 跳到结算页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/tosettle")
    public String toSettlement(Model model) throws Exception {
    	
        List<ProductCategoryVo> productCategoryVoList =categoryService.queryAllProductCategoryList();
        //封装返回
        model.addAttribute("productCategoryVoList", productCategoryVoList);
        
        return "pre/settlement/toSettlement";
    }
	


    /**
     * @param request
     * @return
     */
   
    private User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        return user;
    }
    /**
     * 跳转到购物车页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/tosettlement1")
    public String settlement1(HttpServletRequest request) throws Exception {
    	ShoppingCart cart = getCartFromSession(request);
    	cart = cartService.calulate(cart);
    	request.getSession().setAttribute("cart",cart);
    	return "pre/settlement/settlement1";
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/tosettlement2")
    public String settlement2(HttpServletRequest request,Model model) throws Exception {
    	
        User user = this.getUserFromSession(request);
        System.out.println("====================="+user.getId());
        List<UserAddress> userAddressList = uerAddressService.findAddressInfos(user.getId(),0);
        
        model.addAttribute("userAddressList", userAddressList);
        
        return "pre/settlement/settlement2";
    }
    
    
    /**
     * 清空购物车
     *
     * @param request
     * @param response
     */
    public String clearCart(HttpServletRequest request) throws Exception {
      
        //结账后清空购物车
        request.getSession().removeAttribute("cart");
   
        return "";
    }
    
    private ReturnResult checkCart(HttpServletRequest request) throws Exception {
    	ReturnResult result = new ReturnResult();
    	HttpSession session = request.getSession();
    	ShoppingCart cart = getCartFromSession(request);
    	cart = cartService.calulate(cart);
    	for (ShoppingCartItem item : cart.getItems()) {
           Product product=productService.findById(item.getProduct().getId());
           if(product.getStock()<item.getQuantity()){
        	   return result.returnFail(product.getName()+"商品数量不足");
           }
        }
    	return result.returnSuccess();
    }
    
    
    
   
}

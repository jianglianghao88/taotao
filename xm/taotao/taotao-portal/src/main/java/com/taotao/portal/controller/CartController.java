package com.taotao.portal.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId , Integer num , HttpServletRequest request,
			HttpServletResponse response){
		
		TaotaoResult result = cartService.addCart(itemId, num, request, response);
		
		return "cartSuccess";
	}
	
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request , Map<String,Object> map){
		
		List<CartItem> list = cartService.getItems(request);
		
		map.put("cartList", list);
		
		return "cart";
	}
	
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public TaotaoResult updateCartItem(@PathVariable long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response){
		TaotaoResult result = cartService.updateCartItem(itemId, num, request, response);

		return result;
	}
	
	
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable long itemId , HttpServletRequest request , HttpServletResponse response){
		
		TaotaoResult result = cartService.deleteCartItem(itemId, request, response);
	
		return "redirect:/cart/cart.html";
	}
	
}

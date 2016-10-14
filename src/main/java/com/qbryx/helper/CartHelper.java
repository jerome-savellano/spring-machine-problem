package com.qbryx.helper;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.qbryx.domain.Cart;
import com.qbryx.service.CustomerService;

@Component("cartHelper")
public class CartHelper {
		
	public void populateCartInLayout(CustomerService customerService, long cartId, Model model){
		
		Cart cart = customerService.getCart(cartId);

		model.addAttribute("productsInCart", cart.getCartProducts());
		model.addAttribute("totalAmount", cart.getTotal());
	}
}

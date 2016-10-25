package com.qbryx.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.User;
import com.qbryx.service.CustomerService;

@Component("cartHelper")
public class CartHelper {
		
	public void populateCartInLayout(CustomerService customerService, User user, Model model){
		
		List<CartProduct> cartProducts = customerService.getProductsInCart(user);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(CartProduct cartProduct : cartProducts){
			totalAmount = totalAmount.add(cartProduct.totalPrice());
		}
		
		model.addAttribute("productsInCart", cartProducts);
		model.addAttribute("totalAmount", totalAmount);
	}
}

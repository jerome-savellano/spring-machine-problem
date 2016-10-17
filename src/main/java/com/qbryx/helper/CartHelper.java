package com.qbryx.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.qbryx.domain.CartProduct;
import com.qbryx.service.CustomerService;

@Component("cartHelper")
public class CartHelper {
		
	public void populateCartInLayout(CustomerService customerService, long cartId, Model model){
		
		List<CartProduct> cartProducts = customerService.getProductsInCart(cartId);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(CartProduct cartProduct : cartProducts){
			totalAmount = totalAmount.add(cartProduct.totalPrice());
		}
		
		model.addAttribute("productsInCart", cartProducts);
		model.addAttribute("totalAmount", totalAmount);
	}
}

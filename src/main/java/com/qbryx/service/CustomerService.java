package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

public interface CustomerService {

	List<CartProduct> getProductsInCart(User user);
	
	void checkout(User user) throws InsufficientStockException;
		
	CartProduct getProductInCart(User user, long productId);
	
	void addProductInCart(User user, CartProduct cartProduct) throws InsufficientStockException;
	
	void removeProductInCart(CartProduct cartProduct);
}

package com.qbryx.service;

import java.util.List;
import java.util.Map;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.Product;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

public interface CustomerService {

	List<CartProduct> getProductsInCart(User user);
	
	Map<String, List<CartProduct>> checkout(User user) throws InsufficientStockException;
		
	CartProduct getProductInCart(User user, long productId);
	
	int checkStock(Product product);
	
	void addProductInCart(User user, CartProduct cartProduct) throws InsufficientStockException;
	
	void removeProductInCart(CartProduct cartProduct);
}

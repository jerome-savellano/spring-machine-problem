package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;

public interface CustomerService {

	List<CartProduct> getProductsInCart(long cartId);
	List<CartProduct> checkout(long cartId) throws InsufficientStockException;
	int getQuantityOfProductInCart(CartProduct cartProduct);
	
	void addProductInCart(CartProduct cartProduct) throws InsufficientStockException;
	void removeProductInCart(long cartId, String upc);
	void updateProductInCart(long cartId);
}

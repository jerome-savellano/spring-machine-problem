package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;

public interface CartDao {
	
	CartProduct getProductInCart(long userId, String upc);
	List<CartProduct> getProductsInCart(long userId);
	
	void addProductInCart(CartProduct product) throws InsufficientStockException;
	void removeProductInCart(CartProduct cartProduct);
	void checkout(long userId);
	void updateProductQuantityInCart(CartProduct cartProduct) throws InsufficientStockException;
}

package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;

public interface CartDao {
	
	CartProduct getProductInCart(CartProduct cartProduct);
	List<CartProduct> getProductsInCart(long cartId);
	int getQuantity(CartProduct cartProduct);
	
	void addProductInCart(CartProduct product) throws InsufficientStockException;
	void removeProductInCart(long cartId, String upc);
	void updateProductStatusInCart(long cartId);
	void updateProductQuantityInCart(CartProduct cartProduct) throws InsufficientStockException;
}

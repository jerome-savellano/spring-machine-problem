package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;

public interface CartDao {
	
	CartProduct checkProductInCart(long cartId, String upc);
	List<CartProduct> getProductsInCart(long cartId);
	int getQuantity(long cartId, String upc);
	
	void addProductInCart(CartProduct product, long cartId);
	void removeProductInCart(long cartId, String upc);
	void updateProductStatusInCart(long cartId);
	void updateProductQuantityInCart(long userId, CartProduct cartProduct);
}

package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;

public interface CartDao {
	
	CartProduct checkProductInCart(CartProduct cartProduct);
	List<CartProduct> getProductsInCart(long cartId);
	int getQuantity(CartProduct cartProduct);
	
	void addProductInCart(CartProduct product);
	void removeProductInCart(long cartId, String upc);
	void updateProductStatusInCart(long cartId);
	void updateProductQuantityInCart(CartProduct cartProduct);
}

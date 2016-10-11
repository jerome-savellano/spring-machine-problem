package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;

public interface CartDao {
	
	CartProduct checkProductInCart(String cartId, String upc);
	List<CartProduct> getProductsInCart(String cartId);
	int getQuantity(String cartId, String upc);
	
	void addProductInCart(CartProduct product, String cartId);
	void removeProductInCart(String cartId, String upc);
	void updateProductStatusInCart(String cartId);
	void updateProductQuantityInCart(CartProduct cartProduct);
}

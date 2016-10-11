package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;

public interface CustomerService {

	List<CartProduct> getProductsInCart(String cartId);
	String getCartId(int userId);
	int getQuantityOfProductInCart(String cartId, String upc);
	List<CartProduct> checkout(String cartId) throws InsufficientStockException;
	Cart getCart(String cartId);
	
	void addProductInCart(CartProduct cartProduct, String cartId) throws InsufficientStockException;
	void removeProductInCart(String cartId, String upc);
	void updateProductInCart(String cartId);
}

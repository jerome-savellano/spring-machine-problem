package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.Product;
import com.qbryx.exception.InsufficientStockException;

public interface CustomerService {

	List<CartProduct> getProductsInCart(long cartId);
	List<Product> getProductsInCarts(long cartId);
	int getQuantityOfProductInCart(long cartId, String upc);
	List<CartProduct> checkout(long cartId) throws InsufficientStockException;
	Cart getCart(long cartId);
	
	void addProductInCart(CartProduct cartProduct, long cartId) throws InsufficientStockException;
	void removeProductInCart(long cartId, String upc);
	void updateProductInCart(long cartId);
}

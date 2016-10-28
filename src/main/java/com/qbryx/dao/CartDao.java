package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

public interface CartDao {
	
	CartProduct findProductInCart(User user, long id);
	List<CartProduct> getProductsInCart(User user);
	
	void addProductInCart(CartProduct product) throws InsufficientStockException;
	void removeProductInCart(CartProduct cartProduct);
	void checkout(User user);
	void updateProductQuantityInCart(CartProduct cartProduct) throws InsufficientStockException;
}

package com.qbryx.domain;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
	
	private long userId;
	private List<CartProduct> cartProducts;
	
	public Cart(long cartId){
		super();
		this.userId = cartId;
	}
	
	public Cart(long cartId, List<CartProduct> cartProducts) {
		super();
		this.userId = cartId;
		this.cartProducts = cartProducts;
	}

	public long getUserId() {
		return userId;
	}

	public void setCartId(long cartId) {
		this.userId = cartId;
	}
	
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	
	public BigDecimal getTotal(){
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(Product cartProduct : getCartProducts()){
			total = total.add(cartProduct.getPrice());
		}

		return total;
	}
}

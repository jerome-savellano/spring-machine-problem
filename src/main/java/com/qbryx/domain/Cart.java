package com.qbryx.domain;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
	
	private String cartId;
	private List<CartProduct> cartProducts;
	
	public Cart(String cartId){
		super();
		this.cartId = cartId;
	}
	
	public Cart(String cartId, List<CartProduct> cartProducts) {
		super();
		this.cartId = cartId;
		this.cartProducts = cartProducts;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	public BigDecimal getTotal(){
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(CartProduct cartProduct : getCartProducts()){
			total = total.add(cartProduct.getTotal());
		}

		return total;
	}
}

package com.qbryx.domain;

import java.math.BigDecimal;

public class CartProduct extends Product{

	private Product product;
	private int quantity;
	private int isPurchased;
	
	public CartProduct(){
		
	}

	public CartProduct(Product product) {
		super(product.getUpc(), product.getCategory(), product.getName(), product.getDescription(), product.getPrice());
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(int isPurchased) {
		this.isPurchased = isPurchased;
	}
	
	public BigDecimal getTotal(){
		return getPrice().multiply(new BigDecimal(getQuantity()));
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}	
}

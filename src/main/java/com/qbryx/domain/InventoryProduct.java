package com.qbryx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_INVENTORY")
public class InventoryProduct {
		
	private long id;
	
	private Product product;
	
	private int stock;
	
	public InventoryProduct(){
		
	}
	
	public InventoryProduct(Product product, int stock){
		this.product = product;
		this.stock = stock;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@OneToOne
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

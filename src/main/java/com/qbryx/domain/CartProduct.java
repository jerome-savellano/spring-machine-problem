package com.qbryx.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CART")
public class CartProduct {
	
	private long id; 
	
	private long userId;
	
	private Product product;
	
	private int quantity;
	
	private int isPurchased;
		
	public CartProduct(){}
	
	public CartProduct(long id, long userId, Product product, int quantity, int isPurchased) {
		super();
		this.id = id;
		this.userId = userId;
		this.product = product;
		this.quantity = quantity;
		this.isPurchased = isPurchased;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "upc", insertable=false, updatable=false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "is_purchased")
	public int isPurchased() {
		return isPurchased;
	}

	public void setPurchased(int isPurchased) {
		this.isPurchased = isPurchased;
	}
	
	@Transient
	public BigDecimal totalPrice(){
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		totalPrice = getProduct().getPrice().multiply(new BigDecimal(getQuantity()));
		
		return totalPrice;
	}

	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", userId=" + userId + ", product=" + product + ", quantity=" + quantity
				+ ", isPurchased=" + isPurchased + "]";
	}
}

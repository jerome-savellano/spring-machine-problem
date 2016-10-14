package com.qbryx.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Carta {
	
	private long id; 
	
	private long userId;
	
	private List<Product> products;
	
	private int quantity;
	
	private int isPurchased;
	
	public Carta(){}
	
	public Carta(long id, long userId, List<Product> products, int quantity, int isPurchased) {
		super();
		this.id = id;
		this.userId = userId;
		this.products = products;
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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "upc", insertable=false, updatable=false)
	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> product) {
		this.products = product;
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
}

package com.qbryx.domain;

import java.math.BigDecimal;

public class Product {

	private String upc;
	private Category category;
	private String name;
	private String description;
	private BigDecimal price;
	
	public Product(){
		
	}

	public Product(String upc, Category category, String name, String description, BigDecimal price) {
		super();
		this.upc = upc;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

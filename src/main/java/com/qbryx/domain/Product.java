package com.qbryx.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product implements java.io.Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String upc;
	
	private Category category;
	
	private String name;
	
	private String description;

	private BigDecimal price;
	
	public Product(){
		
	}
	
	public Product(String upc){
		this.upc = upc;
	}
	
	public Product(String upc, String name, BigDecimal price){
		this.upc = upc;
		this.name = name;
		this.price = price;
	}

	public Product(String upc, Category category, String name, String description, BigDecimal price) {
		super();
		this.upc = upc;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "upc")
	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

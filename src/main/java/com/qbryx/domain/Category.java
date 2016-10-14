package com.qbryx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {
	
	private long id;
	private String categoryId;
	private String name;
	
	public Category(String categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "category_id")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.qbryx.util;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.service.ProductService;

public class InventoryProductBuilder {
	
	private String name;
	
	@NumberFormat 
	private String upc;
	private String categoryName;
	private String description;
	private BigDecimal price;
	private int stock;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUpc() {
		return upc;
	}
	
	public void setUpc(String upc) {
		this.upc = upc;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public InventoryProduct getInventoryProduct(ProductService productService){
		
		Product product = new Product();
		
		product.setUpc(upc);
		product.setCategory(productService.getCategory(categoryName));
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		
		InventoryProduct inventoryProduct = new InventoryProduct();
		
		inventoryProduct.setProduct(product);
		inventoryProduct.setStock(stock);
		
		return inventoryProduct;
	}
}

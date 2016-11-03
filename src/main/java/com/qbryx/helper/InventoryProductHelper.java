package com.qbryx.helper;

import java.math.BigDecimal;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.service.ProductService;

public class InventoryProductHelper {
	
	private String name;

	private String upc;
	
	private String categoryName;
	
	private String description;
	
	private String price;

	private String stock;

	public InventoryProductHelper(){}
	
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
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getStock() {
		return stock;
	}
	
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public InventoryProduct buildInventoryProduct(ProductService productService){
		
		Product product = new Product();
		
		product.setUpc(upc);
		product.setCategory(productService.getCategory(categoryName));
		product.setName(name);
		product.setDescription(description);
		product.setPrice(new BigDecimal(price));
				
		InventoryProduct inventoryProduct = new InventoryProduct();
		
		inventoryProduct.setProduct(product);
		inventoryProduct.setStock(Integer.parseInt(stock));
		
		return inventoryProduct;
	}
	
	public InventoryProduct getExistingInvetoryProduct(ProductService productService){	
		
		Product product = productService.getProduct(upc);
				
		product.setUpc(upc);
		product.setCategory(productService.getCategory(categoryName));
		product.setName(name);
		product.setDescription(description);
		product.setPrice(new BigDecimal(price));

		InventoryProduct inventoryProduct = new InventoryProduct(product, Integer.parseInt(stock));
		
		return inventoryProduct;
	}
}

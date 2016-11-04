package com.qbryx.helper;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.service.ProductService;
import com.qbryx.util.ValidatePrice;
import com.qbryx.util.ValidateStock;
import com.qbryx.util.ValidateUpc;

public class InventoryProductHelper {
	
	@NotEmpty(message="Your product must have a name.")
	private String name;
	
	@ValidateUpc
	private String upc;
	
	@NotEmpty(message="Please select a category")
	private String categoryName;
	
	private String description;
	
	@ValidatePrice
	private BigDecimal price;
	
	@ValidateStock
	private Integer stock;

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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public InventoryProduct buildInventoryProduct(ProductService productService){
		
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
	
	public InventoryProduct getExistingInvetoryProduct(ProductService productService){	
		
		Product product = productService.getProduct(upc);
				
		product.setUpc(upc);
		product.setCategory(productService.getCategory(categoryName));
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);

		InventoryProduct inventoryProduct = new InventoryProduct(product, stock);
		
		return inventoryProduct;
	}
}

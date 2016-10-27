package com.qbryx.helper;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.service.ProductService;

public class InventoryProductHelper {
	
	@NotNull
	private String name;
	
	@Pattern(regexp="\\d+", message="UPC must contain numbers only")
	@Size(min=12, max=12, message="UPC must be 12 digits only")
	private String upc;
	
	private String categoryName;
	private String description;
	
	private BigDecimal price;
	
	private int stock;
	
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
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
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

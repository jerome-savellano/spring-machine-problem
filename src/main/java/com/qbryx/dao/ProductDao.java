package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String categoryId);
	Product getProduct(String upc);
	InventoryProduct getInventoryProduct(long id);
	
	void addProduct(Product product);
	void addProductStock(InventoryProduct inventoryProduct);
	void updateProduct(Product product);
	void updateStock(InventoryProduct inventoryProduct);
}

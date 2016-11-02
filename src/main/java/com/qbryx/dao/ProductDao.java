package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> findAllProducts();
	
	List<Product> findProductsByCategory(String categoryId);
	
	Product findProductByUpc(String upc);
	
	void addProduct(Product product);
	
	void addStock(InventoryProduct inventoryProduct);
	
	void updateProduct(Product product);
	
	void updateStock(InventoryProduct inventoryProduct);
}

package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String categoryName);
	List<InventoryProduct> getProductInventory(String upc);
	Product getProductByUpc(String upc); //method signature change to getProduct(String upc)
	InventoryProduct getInventoryProductByUpc(String upc); //separate DAO
	int getStock(String cartId);
	
	void addProduct(Product product);
	void addProductStock(InventoryProduct inventoryProduct);
	void updateProduct(Product product);
	void updateProductStock(InventoryProduct inventoryProduct);
}

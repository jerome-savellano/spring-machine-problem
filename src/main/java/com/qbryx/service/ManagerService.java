package com.qbryx.service;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ManagerService {
	
	Product getProductByUpc(String upc);
	
	void addProduct(InventoryProduct inventoryProduct);
	void updateProduct(InventoryProduct inventoryProduct);
}

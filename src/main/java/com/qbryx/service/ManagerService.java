package com.qbryx.service;

import com.qbryx.domain.InventoryProduct;

public interface ManagerService {
	
	InventoryProduct getProduct(String upc);
	
	void addProduct(InventoryProduct inventoryProduct);
	void updateProduct(InventoryProduct inventoryProduct);
}

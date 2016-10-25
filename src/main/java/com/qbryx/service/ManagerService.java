package com.qbryx.service;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.DuplicateProductException;

public interface ManagerService {
	
	InventoryProduct getProduct(String upc);
	
	void add(InventoryProduct inventoryProduct) throws DuplicateProductException;
	void update(InventoryProduct inventoryProduct);
}

package com.qbryx.service;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.DuplicateProductException;
import com.qbryx.exception.ProductNotFoundException;

public interface ManagerService {
	
	InventoryProduct getProduct(String upc) throws ProductNotFoundException;
	
	void add(InventoryProduct inventoryProduct) throws DuplicateProductException;
	void update(InventoryProduct inventoryProduct);
}

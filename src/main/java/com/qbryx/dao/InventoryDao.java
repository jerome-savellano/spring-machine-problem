package com.qbryx.dao;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface InventoryDao {
	
	InventoryProduct findInventoryProduct(Product product);
}

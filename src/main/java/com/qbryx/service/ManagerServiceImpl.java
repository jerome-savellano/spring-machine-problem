package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.ProductDao;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Resource(name="productDao")
	private ProductDao productDao;

	public Product getProductByUpc(String upc) {
		return productDao.getInventoryProductByUpc(upc);
	}

	public void addProduct(InventoryProduct inventoryProduct) {
		productDao.addProduct(inventoryProduct); 
		productDao.addProductStock(inventoryProduct);
	}

	public void updateProduct(InventoryProduct inventoryProduct) {
		productDao.updateProduct(inventoryProduct);
		productDao.updateProductStock(inventoryProduct);
	}
}

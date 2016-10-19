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
	
	@Resource(name="productDaoHQL")
	private ProductDao productDaoHQL;

	public Product getProductByUpc(String upc) {
		return productDao.getInventoryProductByUpc(upc);
	}

	public void addProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.addProduct(inventoryProduct); 
		productDaoHQL.addProductStock(inventoryProduct);
	}

	public void updateProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.updateProduct(inventoryProduct);
		productDaoHQL.updateProductStock(inventoryProduct);
	}
}

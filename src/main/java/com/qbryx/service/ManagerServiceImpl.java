package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.ProductDao;
import com.qbryx.domain.InventoryProduct;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Resource(name="productDaoHQL")
	private ProductDao productDaoHQL;

	public InventoryProduct getProduct(String upc) {		
		return productDaoHQL.getInventoryProductByUpc(upc);
	}

	public void addProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.addProduct(inventoryProduct.getProduct()); 
		productDaoHQL.addProductStock(inventoryProduct);
	}

	public void updateProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.updateProduct(inventoryProduct.getProduct());
		productDaoHQL.updateProductStock(inventoryProduct);
	}
}

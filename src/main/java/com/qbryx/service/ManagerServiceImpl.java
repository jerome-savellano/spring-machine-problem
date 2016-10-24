package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.ProductDao;
import com.qbryx.domain.InventoryProduct;

@Service("managerService")
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Resource(name="productDaoHQL")
	private ProductDao productDaoHQL;

	public InventoryProduct getProduct(String upc) {		
		return productDaoHQL.getInventoryProductByUpc(upc);
	}

	@Transactional(readOnly = false)
	public void addProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.addProduct(inventoryProduct.getProduct()); 
		productDaoHQL.addProductStock(inventoryProduct);
	}

	@Transactional(readOnly = false)
	public void updateProduct(InventoryProduct inventoryProduct) {
		productDaoHQL.updateProduct(inventoryProduct.getProduct());
		productDaoHQL.updateProductStock(inventoryProduct);
	}
}

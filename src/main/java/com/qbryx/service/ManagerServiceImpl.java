package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.ProductDao;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.exception.DuplicateProductException;

@Service("managerService")
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Resource(name="productDaoHQL")
	private ProductDao productDaoHQL;

	public InventoryProduct getProduct(String upc) {		
		
		Product product = productDaoHQL.getProduct(upc);
		
		return productDaoHQL.getInventoryProduct(product.getId());
	}

	@Transactional(readOnly = false)
	public void add(InventoryProduct inventoryProduct) throws DuplicateProductException{
		
		Product product = inventoryProduct.getProduct();
		
		boolean productExists = (productDaoHQL.getProduct(inventoryProduct.getProduct().getUpc()) != null);
		
		if(productExists){
			throw new DuplicateProductException();
		}
		
		productDaoHQL.addProduct(product); 
		productDaoHQL.addProductStock(inventoryProduct);
	}

	@Transactional(readOnly = false)
	public void update(InventoryProduct inventoryProduct) {
		productDaoHQL.updateProduct(inventoryProduct.getProduct());
		productDaoHQL.updateStock(inventoryProduct);
	}
}

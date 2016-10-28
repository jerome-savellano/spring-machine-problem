package com.qbryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.ProductDao;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.exception.DuplicateProductException;
import com.qbryx.exception.ProductNotFoundException;

@Service("managerService")
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

	@Resource(name = "productDaoHQL")
	private ProductDao productDao;

	public InventoryProduct getProduct(String upc) throws ProductNotFoundException {

		Product product = productDao.findProductByUpc(upc);

		if (product == null) {
			throw new ProductNotFoundException();
		}

		return productDao.findInventoryProductById(product.getId()); // create
																		// separated
																		// DAO
																		// for
																		// inventory
																		// product
	}

	@Transactional(readOnly = false)
	public void add(InventoryProduct inventoryProduct) throws DuplicateProductException {

		Product product = inventoryProduct.getProduct();

		boolean productExists = (productDao.findProductByUpc(inventoryProduct.getProduct().getUpc()) != null);

		if (productExists) {
			throw new DuplicateProductException();
		}

		productDao.addProduct(product);
		productDao.addStock(inventoryProduct);
	}

	@Transactional(readOnly = false)
	public void update(InventoryProduct inventoryProduct) {
		productDao.updateProduct(inventoryProduct.getProduct());
		productDao.updateStock(inventoryProduct);
	}
}

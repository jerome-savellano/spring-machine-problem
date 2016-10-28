package com.qbryx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.CategoryDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.domain.Category;
import com.qbryx.domain.Product;

@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
	@Resource(name="productDaoHQL")
	private ProductDao productDao;
	
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public List<Product> getProductsByCategory(String categoryName) {
		return productDao.findProductsByCategory(categoryName);
	}

	public Product getProduct(String upc) {
		return productDao.findProductByUpc(upc);
	}

	public Category getCategory(String categoryName) {
		return categoryDao.getCategory(categoryName);
	}
}

package com.qbryx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.CategoryDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.domain.Category;
import com.qbryx.domain.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
	@Resource(name="productDao")
	private ProductDao productDao;
	
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public List<Product> getProductsByCategory(String categoryName) {
		return productDao.getProductsByCategory(categoryName);
	}

	public Product getProductByUpc(String upc) {
		return productDao.getProductByUpc(upc);
	}

	public Category getCategory(String categoryName) {
		return categoryDao.getCategory(categoryName);
	}

}

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
	
	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Resource(name="productDaoHQL")
	private ProductDao productDaoHQL;
	
	@Resource(name="productDaoCriteria")
	private ProductDao productDaoCriteria;
	
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public List<Product> getProductsByCategory(String categoryName) {
		return productDaoHQL.getProductsByCategory(categoryName);
	}

	public Product getProduct(String upc) {
		return productDaoHQL.getProductByUpc(upc);
	}

	public Category getCategory(String categoryName) {
		return categoryDao.getCategory(categoryName);
	}
}

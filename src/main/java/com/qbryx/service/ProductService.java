package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.Category;
import com.qbryx.domain.Product;

public interface ProductService {
	
	List<Category> getCategories();
	Category getCategory(String categoryName);
	List<Product> getProductsByCategory(String categoryName);
	Product getProduct(String upc);
}

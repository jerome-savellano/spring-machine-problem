package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.Category;

public interface CategoryDao {
	
	List<Category> getCategories();
	Category getCategory(String categoryName);
}

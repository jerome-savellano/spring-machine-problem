package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.Category;
import com.qbryx.managers.ConnectionManager;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	
	private static final String GET_CATEGORIES = "select category_id, name from category";
	private static final String GET_CATEGORY = "select category_id, name from category where name = ?";

	public List<Category> getCategories() {
		
		List<Category> categories = new ArrayList<Category>();
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(GET_CATEGORIES);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					Category category = new Category(rs.getString("category_id"), rs.getString("name"));
					categories.add(category);
					
					ConnectionManager.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}

		return categories;
	}

	public Category getCategory(String categoryName) {
		
		Category category = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(GET_CATEGORY);
				stmt.setString(1, categoryName);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					category = new Category(rs.getString("category_id"), rs.getString("name"));
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}

		return category;
	}
}

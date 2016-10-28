package com.qbryx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.Category;
import com.qbryx.manager.ConnectionManager;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	
	private static final String GET_CATEGORIES = "select id, name from category";
	private static final String GET_CATEGORY = "select id, name from category where name = ?";

	public List<Category> getCategories() {
		
		Connection connection = ConnectionManager.getConnection();
		
		List<Category> categories = new ArrayList<Category>();
		
		if(connection != null){
			PreparedStatement stmt;
				
			try {
				stmt = connection.prepareStatement(GET_CATEGORIES);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					Category category = new Category(rs.getLong("id"), rs.getString("name"));
					categories.add(category);	
				}
				
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}
		
		return categories;
	}

	public Category getCategory(String categoryName) {
		
		Connection connection = ConnectionManager.getConnection();
		
		Category category = null;
		
		if(connection != null){
			PreparedStatement stmt;
				
			try {
				stmt = connection.prepareStatement(GET_CATEGORY);
				stmt.setString(1, categoryName);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					category = new Category(rs.getLong("id"), rs.getString("name"));
				}
				
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}

		return category;
	}
}

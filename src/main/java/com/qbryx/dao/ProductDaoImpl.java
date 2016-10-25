package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.Category;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.managers.ConnectionManager;
import com.qbryx.util.DAOQuery;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	public List<Product> getAllProducts() {
		throw new UnsupportedOperationException();
	}

	public List<Product> getProductsByCategory(String categoryId) {
		
		List<Product> products = new ArrayList<Product>();
			
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_PRODUCTS_BY_CATEGORY);
				stmt.setString(1, categoryId);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					
					product.setCategory(new Category(rs.getLong("id"), rs.getString("name")));
					product.setName(rs.getString("name"));
					product.setUpc(rs.getString("upc"));
					
					products.add(product);
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		

		return products;
	}

	public Product getProduct(String upc) {
		
		Product product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_PRODUCT_BY_UPC_P);
				stmt.setString(1,  upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					product = new Product();
					product.setUpc(rs.getString("upc"));
					product.setName(rs.getString("name"));
					product.setCategory(new Category(rs.getLong("id"), rs.getString("name")));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getBigDecimal("price"));
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		

		return product;
	}

	public int getStock(String upc) {
		
		int stock = 0;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
			stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_PRODUCT_QUANTITY_ON_HAND);
				stmt.setString(1, upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					stock = rs.getInt("stock");
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
			
		}
		
		
		return stock;
	}

	public InventoryProduct getInventoryProduct(long id) {
		
		InventoryProduct product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_GET_PRODUCT_BY_UPC_M);
				stmt.setLong(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					Product mProduct = new Product();
					mProduct.setName(rs.getString("name"));
					mProduct.setPrice(rs.getBigDecimal("price"));
					mProduct.setUpc(rs.getString("upc"));
					mProduct.setCategory(new Category(rs.getLong("id"), rs.getString("name")));
					mProduct.setDescription(rs.getString("description"));
					
					product = new InventoryProduct(mProduct, rs.getInt("stock"));
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		
		return product;
	}

	public void addProduct(Product product) {
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_ADD_PRODUCT);
				stmt.setLong(1, product.getCategory().getCategoryId());
				stmt.setString(2, product.getUpc());
				stmt.setString(3, product.getName());
				stmt.setString(4, product.getDescription());
				stmt.setBigDecimal(5, product.getPrice());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	public void addProductStock(InventoryProduct inventoryProduct) {
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_ADD_PRODUCT_STOCK);
				stmt.setString(1, inventoryProduct.getProduct().getUpc());
				stmt.setInt(2, inventoryProduct.getStock());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	public void updateProduct(Product product) {
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_UPDATE_PRODUCT);		
				stmt.setString(1, product.getName());
				stmt.setString(2, product.getDescription());
				stmt.setBigDecimal(3, product.getPrice());
				stmt.setString(4, product.getUpc());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	public void updateStock(InventoryProduct inventoryProduct) {
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(DAOQuery.SQL_UPDATE_PRODUCT_INVENTORY);
				stmt.setInt(1, inventoryProduct.getStock());
				stmt.setString(2, inventoryProduct.getProduct().getUpc());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}
}

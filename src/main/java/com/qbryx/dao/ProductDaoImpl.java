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

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	private static final String GET_PRODUCTS_BY_CATEGORY = "select product.upc, product.name, product.category_id, category.name from product inner join category on product.category_id = category.category_id where category.name = ?";
	private static final String GET_PRODUCT_BY_UPC_P = "select upc, name, category_id, description, price from product where upc = ?";
	private static final String GET_PRODUCT_BY_UPC_M = "select p.name, p.price, p.upc, c.category_id, c.name as cname, p.description, pi.stock from product p inner join category c on p.category_id = c.category_id inner join product_inventory pi on p.upc = pi.upc  where p.upc = ?";
	private static final String GET_PRODUCT_QUANTITY_ON_HAND = "select stock, upc from product_inventory where upc = ?";
	private static final String GET_INVENTORY_PRODUCT = "select p.upc, p.stock from product_inventory p inner join customer_product_in_cart c on p.upc = c.upc where c.cart_id = 'jersav'";
	private static final String ADD_PRODUCT = "insert into product (category_id, upc, name, description, price) values (?,?,?,?,?);";
	private static final String ADD_PRODUCT_STOCK = "insert into product_inventory (upc, stock) values (?,?);";
	private static final String UPDATE_PRODUCT = "UPDATE `qbryx`.`product` SET `name` = ?, `description` = ?, `price` = ? WHERE `upc` = ?;";
	private static final String UPDATE_PRODUCT_INVENTORY = "UPDATE `qbryx`.`product_inventory` SET `stock` = ? WHERE `upc` = ?;";
	
	public List<Product> getAllProducts() {
		throw new UnsupportedOperationException();
	}

	public List<Product> getProductsByCategory(String categoryName) {
		
		List<Product> products = new ArrayList<Product>();
			
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCTS_BY_CATEGORY);
				stmt.setString(1, categoryName);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					
					product.setCategory(new Category(rs.getString("category_id"), rs.getString("name")));
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

	public Product getProductByUpc(String upc) {
		
		Product product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_BY_UPC_P);
				stmt.setString(1,  upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					product = new Product();
					product.setUpc(rs.getString("upc"));
					product.setName(rs.getString("name"));
					product.setCategory(new Category(rs.getString("category_id"), rs.getString("name")));
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
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_QUANTITY_ON_HAND);
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

	public InventoryProduct getInventoryProductByUpc(String upc) {
		
		InventoryProduct product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_BY_UPC_M);
				stmt.setString(1, upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					Product mProduct = new Product();
					mProduct.setName(rs.getString("name"));
					mProduct.setPrice(rs.getBigDecimal("price"));
					mProduct.setUpc(rs.getString("upc"));
					mProduct.setCategory(new Category(rs.getString("category_id"), rs.getString("cname")));
					mProduct.setDescription(rs.getString("description"));
					
					product = new InventoryProduct(mProduct);
					product.setStock(rs.getInt("stock"));
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
				stmt = ConnectionManager.prepareStatement(ADD_PRODUCT);
				stmt.setString(1, product.getCategory().getCategoryId());
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
				stmt = ConnectionManager.prepareStatement(ADD_PRODUCT_STOCK);
				stmt.setString(1, inventoryProduct.getUpc());
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
				stmt = ConnectionManager.prepareStatement(UPDATE_PRODUCT);		
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

	public void updateProductStock(InventoryProduct inventoryProduct) {
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(UPDATE_PRODUCT_INVENTORY);
				stmt.setInt(1, inventoryProduct.getStock());
				stmt.setString(2, inventoryProduct.getUpc());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	public List<InventoryProduct> getProductInventory(String cartId) {
		
		List<InventoryProduct> inventoryProducts = new ArrayList<InventoryProduct>();
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_INVENTORY_PRODUCT);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					inventoryProducts.add(new InventoryProduct(rs.getString("upc"), rs.getInt("stock")));
				}
				
				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		
		
		return inventoryProducts;
	}

}

package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.managers.ConnectionManager;

@Repository("cartDao")
public class CartDaoImpl implements CartDao {

	private static final String GET_PRODUCTS_IN_CART = "select p.name as name, p.upc as upc, p.price as price, c.quantity as quantity from product p inner join customer_product_in_cart c on p.upc = c.upc where c.cart_id = ? and c.is_purchased = 0";
	private static final String ADD_PRODUCT_IN_CART = "insert into customer_product_in_cart (cart_id, upc, quantity, is_purchased) values(?,?,?,?)";
	private static final String REMOVE_IN_CART = "delete from customer_product_in_cart where cart_id = ? and upc = ?";
	private static final String UPDATE_PRODUCT_IN_CART = "UPDATE `qbryx`.`customer_product_in_cart` SET `is_purchased` = 1 WHERE `cart_id` = ?;";
	private static final String GET_QUANTITY = "select quantity from customer_product_in_cart where cart_id = ? and upc = ? and is_purchased = 0;";
	private static final String CHECK_PRODUCT_IN_CART = "select cart_id, upc, quantity from customer_product_in_cart where cart_id = ? and upc = ? and is_purchased = 0;";
	private static final String UPDATE_PRODUCT_QUANTITY_IN_CART = "UPDATE `qbryx`.`customer_product_in_cart` SET `quantity` = ? WHERE `cart_id` = ? and upc = ? and is_purchased = 0;";

	public void addProductInCart(CartProduct product, String cartId) {

		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(ADD_PRODUCT_IN_CART);
				stmt.setString(1, cartId);
				stmt.setString(2, product.getUpc());
				stmt.setInt(3, product.getQuantity());
				stmt.setInt(4, product.getIsPurchased());

				stmt.executeUpdate();

				ConnectionManager.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public List<CartProduct> getProductsInCart(String cartId) {

		List<CartProduct> cartProducts = new ArrayList<CartProduct>();

		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCTS_IN_CART);
				stmt.setString(1, cartId);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					CartProduct cartProduct = new CartProduct();
					cartProduct.setUpc(rs.getString("upc"));
					cartProduct.setName(rs.getString("name"));
					cartProduct.setPrice(rs.getBigDecimal("price"));
					cartProduct.setQuantity(rs.getInt("quantity"));

					cartProducts.add(cartProduct);

					ConnectionManager.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}

		return cartProducts;
	}

	public void removeProductInCart(String cartId, String upc) {
		
		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(REMOVE_IN_CART);
				stmt.setString(1, cartId);
				stmt.setString(2, upc);

				stmt.executeUpdate();

				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}

		}
	}

	public void updateProductStatusInCart(String cartId) {
		
		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(UPDATE_PRODUCT_IN_CART);
				stmt.setString(1, cartId);

				stmt.executeUpdate();

				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	public int getQuantity(String cartId, String upc) {
		// TODO Auto-generated method stub
		int quantity = 0;

		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(GET_QUANTITY);
				stmt.setString(1, cartId);
				stmt.setString(2, upc);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					quantity = rs.getInt("quantity");
				}
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}

		return quantity;
	}

	public CartProduct checkProductInCart(String cartId, String upc) {

		CartProduct cartProduct = null;

		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(CHECK_PRODUCT_IN_CART);
				stmt.setString(1, cartId);
				stmt.setString(2, upc);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					cartProduct = new CartProduct();
					cartProduct.setCartId(rs.getString("cart_id"));
					cartProduct.setUpc(rs.getString("upc"));
					cartProduct.setQuantity(rs.getInt("quantity"));
					
					ConnectionManager.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException();
			}

		}

		return cartProduct;
	}

	public void updateProductQuantityInCart(CartProduct cartProduct) {

		if (ConnectionManager.getConnection() != null) {
			PreparedStatement stmt;

			try {
				stmt = ConnectionManager.prepareStatement(UPDATE_PRODUCT_QUANTITY_IN_CART);
				stmt.setInt(1, cartProduct.getQuantity());
				stmt.setString(2, cartProduct.getCartId());
				stmt.setString(3, cartProduct.getUpc());

				stmt.executeUpdate();

				ConnectionManager.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

}

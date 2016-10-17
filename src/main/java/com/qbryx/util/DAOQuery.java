package com.qbryx.util;

public class DAOQuery {
	
	//SQL queries
	
	//UserDAO queries
	public static final String SQL_GET_USER = "select id, user_type, username, password from user where username = ?";
	
	//CartDAO queries
	public static final String SQL_GET_PRODUCTS_IN_CART = "select p.name as name, p.upc as upc, p.price as price, c.quantity as quantity from product p inner join cart c on p.upc = c.upc where c.user_id = ? and c.is_purchased = 0";
	public static final String SQL_ADD_PRODUCT_IN_CART = "insert into cart (user_id, upc, quantity, is_purchased) values(?,?,?,?)";
	public static final String SQL_REMOVE_IN_CART = "delete from cart where user_id = ? and upc = ?";
	public static final String SQL_UPDATE_PRODUCT_IN_CART = "UPDATE `qbryx`.`cart` SET `is_purchased` = 1 WHERE `user_id` = ?;";
	public static final String SQL_GET_QUANTITY = "select quantity from cart where user_id = ? and upc = ? and is_purchased = 0;";
	public static final String SQL_CHECK_PRODUCT_IN_CART = "select user_id, upc, quantity from cart where user_id = ? and upc = ? and is_purchased = 0;";
	public static final String SQL_UPDATE_PRODUCT_QUANTITY_IN_CART = "UPDATE `qbryx`.`cart` SET `quantity` = ? WHERE `user_id` = ? and upc = ? and is_purchased = 0;";
	
	//HQL Queries
	
	//User DAO queries
	public static final String HQL_GET_USER = "from User where username = :username";
	
	//CartDAO queries
	public static final String HQL_ADD_PRODUCT_IN_CART = "insert into cart (user_id, upc, quantity, is_purchased) values(:user_id,:upc,:quantity,0)";
	public static final String HQL_GET_PRODUCTS_IN_CART = "from Cart where userId = :userId";
	public static final String HQL_UPDATE_PRODUCT_QUANTITY_IN_CART = "update CartProduct set quantity = :quantity where userId = :userId and upc = :upc and is_purchased = 0";
	public static final String HQL_CHECK_PRODUCT_IN_CART = "from CartProduct where userId = :userId and product = :product";
}

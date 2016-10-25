package com.qbryx.util;

public class DAOQuery {
	
	//SQL queries
	
	//UserDAO queries
	public static final String SQL_GET_USER = "select id, user_type, username, password from user where username = ?";
	public static final String SQL_GET_PASSWORD = "select password from user where username = ?";
	
	//CartDAO queries
	public static final String SQL_GET_PRODUCTS_IN_CART = "select p.name as name, p.upc as upc, p.price as price, c.quantity as quantity from product p inner join cart c on p.id = c.product_id where c.user_id = ? and c.is_purchased = 0";
	public static final String SQL_ADD_PRODUCT_IN_CART = "insert into cart (user_id, product_id, quantity, is_purchased) values(?,?,?,?)";
	public static final String SQL_REMOVE_PRODUCT_IN_CART = "delete from cart where user_id = ? and product_id = ?";
	public static final String SQL_UPDATE_PRODUCT_IN_CART = "UPDATE `qbryx`.`cart` SET `is_purchased` = 1 WHERE `user_id` = ?;";
	public static final String SQL_CHECK_PRODUCT_IN_CART = "select user_id, product_id, quantity from cart where user_id = ? and product_id = ? and is_purchased = 0;";
	public static final String SQL_UPDATE_PRODUCT_QUANTITY_IN_CART = "UPDATE `qbryx`.`cart` SET `quantity` = ? WHERE `user_id` = ? and product_id = ? and is_purchased = 0;";
	
	//ProductDAO queries
	public static final String SQL_GET_PRODUCTS_BY_CATEGORY = "select product.upc, product.name, product.category_id, category.name from product inner join category on product.category_id = category.id where category.id = ?";
	public static final String SQL_GET_PRODUCT_BY_UPC_P = "select upc, name, category_id, description, price from product where upc = ?";
	public static final String SQL_GET_PRODUCT_BY_UPC_M = "select p.name, p.price, p.upc, c.id, c.name as cname, p.description, pi.stock from product p inner join category c on p.category_id = c.id inner join product_inventory pi on p.id = pi.product_id  where p.id = ?";
	public static final String SQL_GET_PRODUCT_QUANTITY_ON_HAND = "select stock, upc from product_inventory where upc = ?";
	public static final String SQL_ADD_PRODUCT = "insert into product (category_id, upc, name, description, price) values (?,?,?,?,?);";
	public static final String SQL_ADD_PRODUCT_STOCK = "insert into product_inventory (upc, stock) values (?,?);";
	public static final String SQL_UPDATE_PRODUCT = "UPDATE `qbryx`.`product` SET `name` = ?, `description` = ?, `price` = ? WHERE `upc` = ?;";
	public static final String SQL_UPDATE_PRODUCT_INVENTORY = "UPDATE `qbryx`.`product_inventory` SET `stock` = ? WHERE `upc` = ?;";
	
	
	//HQL Queries
	
	//User DAO queries
	public static final String HQL_GET_USER = "from User where username = :username";
	
	//ProductDAO queries
	public static final String HQL_GET_PRODUCTS_BY_CATEGORY = "from Product where category.name = :category";
	public static final String HQL_ADD_PRODUCT ="insert into Product (upc, category_id, name, description, price) values(:upc,:category,:name,:description,:price)";
	public static final String HQL_GET_PRODUCT_BY_UPC = "from Product where upc = :upc";
	public static final String HQL_GET_INVENTORY_PRODUCT = "from InventoryProduct where product_id = :product_id";
	public static final String HQL_ADD_PRODUCT_STOCK = "insert into product_inventory (upc, stock) values (:upc,:stock);";
	public static final String HQL_UPDATE_PRODUCT = "update Product set name = :name, description = :description, price = :price where upc = :upc";
	public static final String HQL_UPDATE_INVENTORY = "update InventoryProduct set stock = :stock where product_id = :product_id";
	
	//CartDAO queries
	public static final String HQL_GET_PRODUCTS_IN_CART = "from CartProduct where userId = :userId and is_purchased = 0";
	public static final String HQL_UPDATE_PRODUCT_QUANTITY_IN_CART = "update CartProduct set quantity = :quantity where user_id = :userId and product_id = :product_id and is_purchased = 0";
	public static final String HQL_CHECK_PRODUCT_IN_CART = "from CartProduct where userId = :userId and product_id = :product_id and is_purchased = 0";
	public static final String HQL_UPDATE_PRODUCT_IN_CART = "update CartProduct set is_purchased = 1 where userId = :userId";
	public static final String HQL_REMOVE_PRODUCT_FROM_CART = "delete from CartProduct where userId = :userId and product_id = :product_id";
}

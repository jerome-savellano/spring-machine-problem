package com.qbryx.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.util.DAOQuery;
import com.qbryx.util.HibernateUtil;

@Repository("cartDaoHQL")
public class CartDaoHQLImpl implements CartDao {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public CartProduct checkProductInCart(CartProduct cartProduct) {
		
		CartProduct product = null;
		
		product = (CartProduct) hibernateUtil.setUpQuery(DAOQuery.HQL_CHECK_PRODUCT_IN_CART)
											 .setParameter("userId", cartProduct.getUserId())
											 .setParameter("product", cartProduct.getProduct())
											 .getSingleResult();

		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> getProductsInCart(long cartId) {

		List<CartProduct> cartProducts = new ArrayList<>();

		hibernateUtil.setUp();
		cartProducts = hibernateUtil.setUpQuery(DAOQuery.HQL_GET_PRODUCTS_IN_CART).setParameter("userId", cartId)
				.getResultList();
		hibernateUtil.commit();

		return cartProducts;
	}

	@Override
	public int getQuantity(CartProduct cartProduct) {
		return 0;
	}

	@Override
	public void addProductInCart(CartProduct product) {

		hibernateUtil.setUp();
		hibernateUtil.setUpSQLQuery(DAOQuery.HQL_ADD_PRODUCT_IN_CART).setParameter("user_id", product.getUserId())
				.setParameter("upc", product.getProduct().getUpc()).setParameter("quantity", product.getQuantity())
				.executeUpdate();
		hibernateUtil.commit();
	}

	@Override
	public void removeProductInCart(long cartId, String upc) {

	}

	@Override
	public void updateProductStatusInCart(long cartId) {

	}

	@Override
	public void updateProductQuantityInCart(CartProduct cartProduct) {
		hibernateUtil.setUp();

		hibernateUtil.setUpQuery(DAOQuery.HQL_UPDATE_PRODUCT_QUANTITY_IN_CART)
					 .setParameter("quantity", cartProduct.getQuantity())
					 .setParameter("userId", cartProduct.getUserId())
					 .setParameter("upc", cartProduct.getProduct().getUpc())
					 .executeUpdate();

		hibernateUtil.commit();
	}
}

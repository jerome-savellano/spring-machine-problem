package com.qbryx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.util.DAOQuery;

@Repository("cartDaoHQL")
public class CartDaoHQLImpl implements CartDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public CartProduct getProductInCart(long userId, String upc) {

		CartProduct product = null;

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_CHECK_PRODUCT_IN_CART).setParameter("userId", userId)
				.setParameter("upc", upc);

		product = (CartProduct) query.getSingleResult();

		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> getProductsInCart(long userId) {

		List<CartProduct> cartProducts = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCTS_IN_CART).setParameter("userId", userId);

		cartProducts = query.getResultList();

		return cartProducts;
	}

	@Override
	public void addProductInCart(CartProduct product) {

		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void removeProductInCart(CartProduct cartProduct) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_REMOVE_PRODUCT_FROM_CART)
				.setParameter("userId", cartProduct.getUserId()).setParameter("upc", cartProduct.getProduct().getUpc());

		query.executeUpdate();
	}

	@Override
	public void checkout(long userId) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_UPDATE_PRODUCT_IN_CART).setParameter("userId", userId);

		query.executeUpdate();

	}

	@Override
	public void updateProductQuantityInCart(CartProduct cartProduct) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_UPDATE_PRODUCT_QUANTITY_IN_CART)
				.setParameter("quantity", cartProduct.getQuantity()).setParameter("userId", cartProduct.getUserId())
				.setParameter("upc", cartProduct.getProduct().getUpc());

		query.executeUpdate();

	}
}

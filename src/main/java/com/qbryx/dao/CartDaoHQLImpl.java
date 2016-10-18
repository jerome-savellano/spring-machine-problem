package com.qbryx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
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
	public CartProduct getProductInCart(CartProduct cartProduct) {

		CartProduct product = null;

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_CHECK_PRODUCT_IN_CART);
			query.setParameter("userId", cartProduct.getUserId());
			query.setParameter("upc", cartProduct.getProduct().getUpc());

			product = (CartProduct) query.getSingleResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> getProductsInCart(long cartId) {

		List<CartProduct> cartProducts = new ArrayList<>();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCTS_IN_CART).setParameter("userId", cartId);

			cartProducts = query.getResultList();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cartProducts;
	}

	@Override
	public void addProductInCart(CartProduct product) {

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_ADD_PRODUCT_IN_CART)
								 .setParameter("user_id", product.getUserId())
								 .setParameter("upc", product.getProduct().getUpc())
								 .setParameter("quantity", product.getQuantity());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void removeProductInCart(long cartId, String upc) {

	}

	@Override
	public void updateProductStatusInCart(long cartId) {

	}

	@Override
	public void updateProductQuantityInCart(CartProduct cartProduct) {
		
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_UPDATE_PRODUCT_QUANTITY_IN_CART)
								 .setParameter("quantity", cartProduct.getQuantity())
								 .setParameter("userId", cartProduct.getUserId())
								 .setParameter("upc", cartProduct.getProduct().getUpc());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public int getQuantity(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		int quantity = 0;
		
		Session session = sessionFactory.openSession();
		
		CartProduct product = null;

		try {
			session.beginTransaction();

			product = getProductInCart(cartProduct);
						
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.beginTransaction() != null)
				session.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		if (product != null) {
			quantity = product.getQuantity();
		}

		return quantity;
	}
}

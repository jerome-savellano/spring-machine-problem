package com.qbryx.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;

@Repository("cartDaoCriteria")
public class CartDaoCriteriaImpl implements CartDao {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name = "cartDaoHQL")
	private CartDao cartDao;
		
	@SuppressWarnings("deprecation")
	@Override
	public CartProduct getProductInCart(long userId, String upc) {
		
		CartProduct cartProduct = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(CartProduct.class)
									   .add(Restrictions.eq("userId", userId))
									   .add(Restrictions.eq("product.upc", upc))
									   .add(Restrictions.eq("isPurchased", 0));
			
			cartProduct = (CartProduct) criteria.uniqueResult();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return cartProduct;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<CartProduct> getProductsInCart(long userId) {
		
		List<CartProduct> cartProducts = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(CartProduct.class)
									   .add(Restrictions.eq("userId", userId))
									   .add(Restrictions.eq("isPurchased", 0));
			
			cartProducts = (List<CartProduct>) criteria.list();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return cartProducts;
	}

	@Override
	public void addProductInCart(CartProduct product) throws InsufficientStockException {
		cartDao.addProductInCart(product);
	}

	@Override
	public void removeProductInCart(CartProduct cartProduct) {
		cartDao.removeProductInCart(cartProduct);
	}

	@Override
	public void checkout(long userId) {
		cartDao.checkout(userId);
	}

	@Override
	public void updateProductQuantityInCart(CartProduct cartProduct) throws InsufficientStockException {
		cartDao.updateProductQuantityInCart(cartProduct);
	}
}

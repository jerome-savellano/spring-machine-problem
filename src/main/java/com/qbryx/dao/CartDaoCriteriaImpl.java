package com.qbryx.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

@Repository("cartDaoCriteria")
public class CartDaoCriteriaImpl implements CartDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Resource(name = "cartDaoHQL")
	private CartDao cartDao;

	@SuppressWarnings("deprecation")
	@Override
	public CartProduct findProductInCart(User user, long id) {

		CartProduct cartProduct = null;

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(CartProduct.class)
				                   .add(Restrictions.eq("userId", user.getUserId()))
								   .add(Restrictions.eq("product.id", id))
								   .add(Restrictions.eq("isPurchased", 0));

		cartProduct = (CartProduct) criteria.uniqueResult();

		return cartProduct;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<CartProduct> getProductsInCart(User user) {

		List<CartProduct> cartProducts = null;

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(CartProduct.class)
				                   .add(Restrictions.eq("userId", user.getUserId()))
				                   .add(Restrictions.eq("isPurchased", 0));

		cartProducts = (List<CartProduct>) criteria.list();

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
	public void checkout(User user) {
		cartDao.checkout(user);
	}

	@Override
	public void updateProductQuantityInCart(CartProduct cartProduct) throws InsufficientStockException {
		cartDao.updateProductQuantityInCart(cartProduct);
	}
}

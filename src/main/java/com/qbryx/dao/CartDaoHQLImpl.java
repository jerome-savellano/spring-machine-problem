package com.qbryx.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.CartProduct;
import com.qbryx.util.DAOQuery;
import com.qbryx.util.HibernateUtil;

@Repository("cartDaoHQL")
public class CartDaoHQLImpl implements CartDao{
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public CartProduct checkProductInCart(long cartId, String upc) {
		return null;
	}

	@Override
	public List<CartProduct> getProductsInCart(long cartId) {
		return null;
	}

	@Override
	public int getQuantity(long cartId, String upc) {
		return 0;
	}

	@Override
	public void addProductInCart(CartProduct product, long cartId) {
		hibernateUtil.getSessionFactory().openSession();
		hibernateUtil.getSession().beginTransaction();
		hibernateUtil.setUpSQLQuery(DAOQuery.HQL_ADD_PRODUCT_IN_CART)
					 .setParameter("user_id", cartId)
					 .setParameter("upc", product.getUpc())
					 .setParameter("quantity", product.getQuantity())
					 .executeUpdate();
		hibernateUtil.getSession().getTransaction().commit();
		hibernateUtil.getSession().close();
	}

	@Override
	public void removeProductInCart(long cartId, String upc) {
		
	}

	@Override
	public void updateProductStatusInCart(long cartId) {
		
	}

	@Override
	public void updateProductQuantityInCart(long cartId, CartProduct cartProduct) {
		
	}

}

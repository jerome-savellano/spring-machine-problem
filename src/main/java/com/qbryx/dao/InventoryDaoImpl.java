package com.qbryx.dao;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.util.DAOQuery;

@Repository("inventoryDao")
public class InventoryDaoImpl implements InventoryDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public InventoryProduct findInventoryProduct(Product product) {
		
		InventoryProduct inventoryProduct = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(DAOQuery.HQL_GET_INVENTORY_PRODUCT);
		
		query.setParameter("product_id", product.getId());
		
		inventoryProduct = (InventoryProduct) query.getSingleResult();
		
		return inventoryProduct;
	}

}

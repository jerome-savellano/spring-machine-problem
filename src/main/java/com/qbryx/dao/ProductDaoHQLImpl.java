package com.qbryx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.util.DAOQuery;

@Repository("productDaoHQL")
public class ProductDaoHQLImpl implements ProductDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getAllProducts() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByCategory(String categoryId) {

		List<Product> products = new ArrayList<>();

		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCTS_BY_CATEGORY).setParameter("category",
					categoryId);

			products = (List<Product>) query.getResultList();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return products;
	}

	@Override
	public Product getProductByUpc(String upc) {

		Product product = null;

		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCT_BY_UPC).setParameter("upc", upc);

			product = (Product) query.getSingleResult();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}

		return product;
	}

	@Override
	public InventoryProduct getInventoryProductByUpc(String upc) {

		InventoryProduct product = null;

		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session.createSQLQuery(DAOQuery.HQL_GET_INVENTORY_PRODUCT)
								 .setParameter("upc", upc);

			product = (InventoryProduct) query.getSingleResult();
			
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}

		return product;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addProduct(Product product) {

		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session.createSQLQuery(DAOQuery.HQL_ADD_PRODUCT).setParameter("upc", product.getUpc())
								 .setParameter("category", product.getCategory().getCategoryId())
								 .setParameter("name", product.getName()).setParameter("description", product.getDescription())
								 .setParameter("price", product.getPrice());

			query.executeUpdate();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void addProductStock(InventoryProduct inventoryProduct) {

		Session session = sessionFactory.openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			@SuppressWarnings("deprecation")
			Query query = session.createSQLQuery(DAOQuery.HQL_ADD_PRODUCT_STOCK)
								 .setParameter("upc", inventoryProduct.getUpc())
								 .setParameter("stock", inventoryProduct.getStock());

			query.executeUpdate();

			transaction.commit();
		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void updateProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(DAOQuery.HQL_UPDATE_PRODUCT)
								 .setParameter("name", product.getName())
								 .setParameter("description", product.getDescription())
								 .setParameter("price", product.getPrice())
								 .setParameter("upc", product.getUpc());
			
			query.executeUpdate();
								 
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}

	@Override
	public void updateProductStock(InventoryProduct inventoryProduct) {
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(DAOQuery.HQL_UPDATE_INVENTORY)
								 .setParameter("stock", inventoryProduct.getStock())
								 .setParameter("upc", inventoryProduct.getUpc());
			
			query.executeUpdate();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}

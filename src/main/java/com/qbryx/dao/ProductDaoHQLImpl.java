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
public class ProductDaoHQLImpl implements ProductDao{
	
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
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCTS_BY_CATEGORY)
								 .setParameter("category", categoryId);
			
			products = (List<Product>) query.getResultList();
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return products;
	}

	@Override
	public List<InventoryProduct> getProductInventory(String upc) {
		return null;
	}

	@Override
	public Product getProductByUpc(String upc) {
		
		Product product = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCT_BY_UPC)
					 			 .setParameter("upc", upc);
			
			product = (Product) query.getSingleResult();
				
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return product;
	}

	@Override
	public InventoryProduct getInventoryProductByUpc(String upc) {
		return null;
	}

	@Override
	public int getStock(String upc) {
		
		int stock = 0;
				
		return stock;
	}

	@Override
	public void addProduct(Product product) {
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		@SuppressWarnings("unused")
		Integer id = 0;
				
		try{
			transaction = session.beginTransaction();
			
			Product newProduct = product;
			
			id = (Integer) session.save(newProduct);
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		
	}

	@Override
	public void addProductStock(InventoryProduct inventoryProduct) {
	}

	@Override
	public void updateProduct(Product product) {
	}

	@Override
	public void updateProductStock(InventoryProduct inventoryProduct) {
	}
}

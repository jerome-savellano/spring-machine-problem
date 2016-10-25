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

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

@Repository("productDaoCriteria")
public class ProductDaoCriteriaImpl implements ProductDao {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name = "productDaoHQL")
	private ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Product> getProductsByCategory(String name) {
		
		List<Product> products = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Product.class)
									   .createAlias("category", "c")
									   .add(Restrictions.eq("c.name", name));
									   
			products = (List<Product>) criteria.list();					   
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return products;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(String upc) {
		
		Product product = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Product.class)
									   .add(Restrictions.eq("upc", upc));
									   
			product = (Product) criteria.uniqueResult();					   
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return product;
	}

	@SuppressWarnings("deprecation")
	@Override
	public InventoryProduct getInventoryProduct(long id) {
		
		InventoryProduct inventoryProduct = null;
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(InventoryProduct.class)
									   .add(Restrictions.eq("product.id", id));
									   
			inventoryProduct = (InventoryProduct) criteria.uniqueResult();					   
			
			transaction.commit();
		}catch(HibernateException e){
			if(transaction != null) transaction.rollback();
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return inventoryProduct;
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Override
	public void addProductStock(InventoryProduct inventoryProduct) {
		productDao.addProductStock(inventoryProduct);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Override
	public void updateStock(InventoryProduct inventoryProduct) {
		productDao.updateStock(inventoryProduct);
	}
}

package com.qbryx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.util.DAOQuery;

@Repository("productDaoHQL")
public class ProductDaoHQLImpl implements ProductDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findAllProducts() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductsByCategory(String categoryId) {

		List<Product> products = new ArrayList<>();

		Session session = sessionFactory.openSession();

		Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCTS_BY_CATEGORY).setParameter("category", categoryId);

		products = (List<Product>) query.getResultList();

		return products;
	}

	@Override
	public Product findProductByUpc(String upc) {

		Product product = null;

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_GET_PRODUCT_BY_UPC).setParameter("upc", upc);

		product = (Product) query.getSingleResult();

		return product;
	}

	@Override
	public void addProduct(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void addStock(InventoryProduct inventoryProduct) {
		sessionFactory.getCurrentSession().save(inventoryProduct);
	}

	@Override
	public void updateProduct(Product product) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_UPDATE_PRODUCT).setParameter("name", product.getName())
				.setParameter("description", product.getDescription()).setParameter("price", product.getPrice())
				.setParameter("upc", product.getUpc());

		query.executeUpdate();
	}

	@Override
	public void updateStock(InventoryProduct inventoryProduct) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(DAOQuery.HQL_UPDATE_INVENTORY)
				.setParameter("stock", inventoryProduct.getStock())
				.setParameter("product_id", inventoryProduct.getProduct().getId());

		query.executeUpdate();
	}
}

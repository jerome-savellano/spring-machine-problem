package com.qbryx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.InventoryDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

@Service("customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "cartDaoHQL")
	private CartDao cartDao;

	@Resource(name = "productDaoHQL")
	private ProductDao productDao;
	
	@Resource(name = "inventoryDao")
	private InventoryDao inventoryDao;

	@Transactional(readOnly = false)
	public void addProductInCart(User user, CartProduct cartProduct) throws InsufficientStockException {

		CartProduct productInCart = cartDao.findProductInCart(user, cartProduct.getProduct().getId());

		boolean inCart = (productInCart != null);

		int stockOnHand = inventoryDao.findInventoryProduct(cartProduct.getProduct()).getStock();

		int quantity = (inCart) ? productInCart.getQuantity() + cartProduct.getQuantity() : cartProduct.getQuantity();

		boolean stockAvailable = (quantity <= stockOnHand);

		if (stockAvailable) {

			if (inCart) {

				productInCart.setQuantity(quantity);
				cartDao.updateProductQuantityInCart(productInCart);
			} else {

				cartDao.addProductInCart(cartProduct);
			}
		} else {

			throw new InsufficientStockException();
		}
	}

	public List<CartProduct> getProductsInCart(User user) {
		return cartDao.getProductsInCart(user);
	}

	@Transactional(readOnly = false)
	public void removeProductInCart(CartProduct cartProduct) {
		cartDao.removeProductInCart(cartProduct);
	}

	@Transactional(readOnly = false, rollbackFor = InsufficientStockException.class)
	public Map<String, List<CartProduct>> checkout(User user) throws InsufficientStockException {
		
		Map<String, List<CartProduct>> checkoutRecord = new HashMap<>();
		
		List<CartProduct> checkedOutProducts = new ArrayList<>();
		
		List<CartProduct> rejectedProducts = new ArrayList<>();

		List<CartProduct> cartProducts = getProductsInCart(user);
		
		for (CartProduct cartProduct : cartProducts) {

			InventoryProduct inventoryProduct = inventoryDao.findInventoryProduct(cartProduct.getProduct());

			boolean stockAvailable = (inventoryProduct.getStock() >= cartProduct.getQuantity());

			if (stockAvailable) {
				
				cartDao.checkout(cartProduct);

				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
						
				productDao.updateStock(inventoryProduct);

				checkedOutProducts.add(cartProduct);
			} else {
				
				rejectedProducts.add(cartProduct);
			}
		}
		
		checkoutRecord.put("checkedOutProducts", checkedOutProducts);
		checkoutRecord.put("rejectedProducts", rejectedProducts);
		
		return checkoutRecord;
	}

	@Override
	public CartProduct getProductInCart(User user, long id) {
		return cartDao.findProductInCart(user, id);
	}

	@Override
	public int checkStock(Product product) {
		
		int stock = 0;
		
		InventoryProduct inventoryProduct = inventoryDao.findInventoryProduct(product);
		
		if(inventoryProduct != null){
			
			stock = inventoryProduct.getStock();
		}
		
		return stock;
	}
}

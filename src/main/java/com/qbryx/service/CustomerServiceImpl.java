package com.qbryx.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.UserDao;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.InsufficientStockException;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "cartDao")
	private CartDao cartDao;

	@Resource(name = "cartDaoHQL")
	private CartDao cartDaoHQL;

	@Resource(name = "productDao")
	private ProductDao productDao;
	
	public void addProductInCart(CartProduct cartProduct) throws InsufficientStockException {
		
		// Get product in cart
		CartProduct product = cartDaoHQL.getProductInCart(cartProduct.getUserId(), cartProduct.getProduct().getUpc());
		
		// Check if product is already in cart
		boolean productInCart = product != null;
		
		// Get product stock
		int stockOnHand = productDao.getStock(cartProduct.getProduct().getUpc());

		if (productInCart) {
			
			boolean stockForProductInCartAvailable = (product.getQuantity() + cartProduct.getQuantity()) <= stockOnHand;

			if (stockForProductInCartAvailable) {
				
				int updatedQuantity = product.getQuantity() + cartProduct.getQuantity();
				product.setQuantity(updatedQuantity);

				cartDaoHQL.updateProductQuantityInCart(product);
			}else{
				
				throw new InsufficientStockException();
				
			}
		} else {
			
			boolean stockForNewProductAvailable = cartProduct.getQuantity() <= stockOnHand;
			
			if (stockForNewProductAvailable) {
				
				cartDaoHQL.addProductInCart(cartProduct);
				
			}else{
				
				throw new InsufficientStockException();
				
			}
		}
	}

	public List<CartProduct> getProductsInCart(long cartId) {
		return cartDaoHQL.getProductsInCart(cartId);
	}

	public void removeProductInCart(CartProduct cartProduct) {
		cartDaoHQL.removeProductInCart(cartProduct);
	}

	public List<CartProduct> checkout(long userId) throws InsufficientStockException {
		List<CartProduct> invalidProduct = new ArrayList<CartProduct>();

		List<CartProduct> cartProducts = getProductsInCart(userId);

		for (CartProduct cartProduct : cartProducts) {
			InventoryProduct inventoryProduct = new InventoryProduct(cartProduct.getProduct().getUpc(),
					productDao.getStock(cartProduct.getProduct().getUpc()));

			if (inventoryProduct.getStock() >= cartProduct.getQuantity()) {
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				productDao.updateProductStock(inventoryProduct);
				cartDaoHQL.checkout(userId);
			} else {
				invalidProduct.add(cartProduct);
			}
		}

		return invalidProduct;
	}

	@Override
	public CartProduct getProductInCart(long userId, String upc) {
		return cartDaoHQL.getProductInCart(userId, upc);
	}
}

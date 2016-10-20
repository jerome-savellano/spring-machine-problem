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
	
	@Resource(name = "cartDaoCriteria")
	private CartDao cartDaoCriteria;
	
	@Resource(name = "productDao")
	private ProductDao productDao;

	@Resource(name = "productDaoHQL")
	private ProductDao productDaoHQL;
	
	@Resource(name = "productDaoCriteria")
	private ProductDao productDaoCriteria;

	public void addProductInCart(CartProduct cartProduct) throws InsufficientStockException {
				
		// Get product in cart
		CartProduct product = cartDaoCriteria.getProductInCart(cartProduct.getUserId(), cartProduct.getProduct().getUpc());

		// Check if product is already in cart
		boolean productInCart = product != null;

		// Get product stock
		int stockOnHand = productDaoCriteria.getInventoryProductByUpc(cartProduct.getProduct().getUpc()).getStock();

		if (productInCart) {

			boolean stockForProductInCartAvailable = (product.getQuantity() + cartProduct.getQuantity()) <= stockOnHand;

			if (stockForProductInCartAvailable) {

				int updatedQuantity = product.getQuantity() + cartProduct.getQuantity();
				product.setQuantity(updatedQuantity);

				cartDaoHQL.updateProductQuantityInCart(product);
			} else {

				throw new InsufficientStockException();

			}
		} else {

			boolean stockForNewProductAvailable = cartProduct.getQuantity() <= stockOnHand;

			if (stockForNewProductAvailable) {

				cartDaoHQL.addProductInCart(cartProduct);
			} else {

				throw new InsufficientStockException();

			}
		}
	}

	public List<CartProduct> getProductsInCart(long cartId) {
		return cartDaoCriteria.getProductsInCart(cartId);
	}

	public void removeProductInCart(CartProduct cartProduct) {
		cartDaoHQL.removeProductInCart(cartProduct);
	}

	public List<CartProduct> checkout(long userId) throws InsufficientStockException {
		List<CartProduct> invalidProduct = new ArrayList<CartProduct>();

		List<CartProduct> cartProducts = getProductsInCart(userId);

		for (CartProduct cartProduct : cartProducts) {
			
			int stock = productDaoCriteria.getInventoryProductByUpc(cartProduct.getProduct().getUpc()).getStock();
		
			InventoryProduct inventoryProduct = new InventoryProduct(cartProduct.getProduct(), stock);

			if (inventoryProduct.getStock() >= cartProduct.getQuantity()) {
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				productDaoHQL.updateProductStock(inventoryProduct);
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

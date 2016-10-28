package com.qbryx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

@Service("customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "cartDaoHQL")
	private CartDao cartDao;

	@Resource(name = "productDaoHQL")
	private ProductDao productDao;

	@Transactional(readOnly = false)
	public void addProductInCart(User user, CartProduct cartProduct) throws InsufficientStockException {

		CartProduct productInCart = cartDao.findProductInCart(user, cartProduct.getProduct().getId());

		boolean inCart = (productInCart != null);

		int stockOnHand = productDao.findInventoryProductById(cartProduct.getProduct().getId()).getStock();

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

	@Transactional(readOnly = false)
	public void checkout(User user) throws InsufficientStockException {

		List<CartProduct> cartProducts = getProductsInCart(user);

		for (CartProduct cartProduct : cartProducts) {

			InventoryProduct inventoryProduct = productDao.findInventoryProductById(cartProduct.getProduct().getId());

			boolean stockAvailable = (inventoryProduct.getStock() >= cartProduct.getQuantity());

			if (stockAvailable) {

				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);

				productDao.updateStock(inventoryProduct);

				cartDao.checkout(user);
			} else {

				throw new InsufficientStockException();
			}
		}
	}

	@Override
	public CartProduct getProductInCart(User user, long id) {
		return cartDao.findProductInCart(user, id);
	}
}

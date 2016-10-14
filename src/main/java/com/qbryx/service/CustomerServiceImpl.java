package com.qbryx.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.UserDao;
import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.InsufficientStockException;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="cartDao")
	private CartDao cartDao;
	
	@Resource(name="cartDaoHQL")
	private CartDao cartDaoHQL;
	
	@Resource(name="productDao")
	private ProductDao productDao;

	public void addProductInCart(CartProduct cartProduct, long cartId) throws InsufficientStockException {
		int stockOnHand = productDao.getStock(cartProduct.getUpc());
		CartProduct product = cartDao.checkProductInCart(cartId, cartProduct.getUpc());

		if (product != null) {
			if ((product.getQuantity() + cartProduct.getQuantity()) <= stockOnHand) {
				product.setQuantity(product.getQuantity() + cartProduct.getQuantity());

				cartDaoHQL.updateProductQuantityInCart(cartId, product);
			}else{
				
				throw new InsufficientStockException();
			}
		} else {
			if (cartProduct.getQuantity() <= stockOnHand) {
				
				cartDaoHQL.addProductInCart(cartProduct, cartId);
			}else{
				
				throw new InsufficientStockException();
			}
		}
	}

	public List<CartProduct> getProductsInCart(long cartId) {
		return cartDao.getProductsInCart(cartId);
	}

	public void removeProductInCart(long cartId, String upc){
		cartDao.removeProductInCart(cartId, upc);
	}

	public void updateProductInCart(long cartId) {
		cartDao.updateProductStatusInCart(cartId);
	}

	public int getQuantityOfProductInCart(long cartId, String upc) {
		return cartDao.getQuantity(cartId, upc);
	}

	public List<CartProduct> checkout(long cartId) throws InsufficientStockException{
		List<CartProduct> invalidProduct = new ArrayList<CartProduct>();

		List<CartProduct> cartProducts = getProductsInCart(cartId);

		for (CartProduct cartProduct : cartProducts) {
			InventoryProduct inventoryProduct = new InventoryProduct(cartProduct.getUpc(),
					productDao.getStock(cartProduct.getUpc()));

			if (inventoryProduct.getStock() >= cartProduct.getQuantity()) {
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				productDao.updateProductStock(inventoryProduct);
				cartDao.updateProductStatusInCart(cartId);
			} else {
				invalidProduct.add(cartProduct);
			}
		}

		return invalidProduct;
	}

	public Cart getCart(long cartId) {
		// TODO Auto-generated method stub
		List<CartProduct> cartProducts = getProductsInCart(cartId);
				
		Cart cart = new Cart(cartId, cartProducts);
		
		return cart;
	}
}

package com.qbryx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.UserDao;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.User;
import com.qbryx.exception.InsufficientStockException;

@Service("customerService")
@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = false)
	public void addProductInCart(User user, CartProduct cartProduct) throws InsufficientStockException {
				
		CartProduct productInCart = cartDaoHQL.getProductInCart(user, cartProduct.getProduct().getId());
		
		boolean inCart = (productInCart != null);
		
		int stockOnHand = productDaoHQL.getInventoryProduct(cartProduct.getProduct().getId()).getStock();
		
		int quantity = (inCart) ? productInCart.getQuantity() + cartProduct.getQuantity() : cartProduct.getQuantity();
		
		boolean stockAvailable = (quantity <= stockOnHand);
		
		if(stockAvailable){
			
			if(inCart){
				
				productInCart.setQuantity(quantity);
				cartDaoHQL.updateProductQuantityInCart(productInCart);		
			}else{
				
				cartDaoHQL.addProductInCart(cartProduct);					
			}
		}else{
			
			throw new InsufficientStockException();
		}
	}

	public List<CartProduct> getProductsInCart(User user) {
		return cartDaoHQL.getProductsInCart(user);
	}
	
	@Transactional(readOnly = false)
	public void removeProductInCart(CartProduct cartProduct) {
		cartDaoHQL.removeProductInCart(cartProduct);
	}

	@Transactional(readOnly = false)
	public void checkout(User user) throws InsufficientStockException {
		
		List<CartProduct> cartProducts = getProductsInCart(user);

		for (CartProduct cartProduct : cartProducts) {
			
			InventoryProduct inventoryProduct = productDaoHQL.getInventoryProduct(cartProduct.getProduct().getId());
					
			boolean stockAvailable = (inventoryProduct.getStock() >= cartProduct.getQuantity());

			if (stockAvailable) {
				
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				
				productDaoHQL.updateStock(inventoryProduct);
				
				cartDaoHQL.checkout(user);
			}else{
				
				throw new InsufficientStockException();
			}
		}
	}

	@Override
	public CartProduct getProductInCart(User user, long id) {
		return cartDaoHQL.getProductInCart(user, id);
	}
}

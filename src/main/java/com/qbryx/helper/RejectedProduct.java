package com.qbryx.helper;

import java.util.ArrayList;
import java.util.List;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.ProductNotFoundException;
import com.qbryx.service.ManagerService;

public class RejectedProduct {
	
	private InventoryProduct inventoryProduct;
	
	private CartProduct cartProduct;
	
	public RejectedProduct(InventoryProduct inventoryProduct, CartProduct cartProduct) {
		super();
		this.inventoryProduct = inventoryProduct;
		this.cartProduct = cartProduct;
	}

	public InventoryProduct getInventoryProduct() {
		return inventoryProduct;
	}

	public void setInventoryProduct(InventoryProduct inventoryProduct) {
		this.inventoryProduct = inventoryProduct;
	}

	public CartProduct getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(CartProduct cartProduct) {
		this.cartProduct = cartProduct;
	}
	
	public static List<RejectedProduct> getRejectedProductsInfo(ManagerService managerService, List<CartProduct> cartProducts) throws ProductNotFoundException{
		
		List<RejectedProduct> rejectedProducts = new ArrayList<>();
		
		for(CartProduct cartProduct : cartProducts){
			
			InventoryProduct inventoryProduct = null;
			
			try {
				
				inventoryProduct = managerService.findProductByUpc(cartProduct.getProduct().getUpc());
			} catch (ProductNotFoundException e) {
				
				throw new ProductNotFoundException();
			}
			
			RejectedProduct rejectedProduct = new RejectedProduct(inventoryProduct, cartProduct);
			
			rejectedProducts.add(rejectedProduct);
		}

		return rejectedProducts;
	}
}

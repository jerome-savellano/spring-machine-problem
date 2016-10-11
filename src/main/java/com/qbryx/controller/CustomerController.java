package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.CartProduct;
import com.qbryx.exception.InsufficientStockException;
import com.qbryx.helper.CartHelper;
import com.qbryx.helper.CustomerHelper;
import com.qbryx.service.CustomerService;
import com.qbryx.service.ProductService;

@Controller
public class CustomerController {

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "cartHelper")
	private CartHelper cartHelper;
	
	@Resource(name = "customerHelper")
	private CustomerHelper customer;
	
	@RequestMapping("/customer")
	public String home(Model model, HttpServletRequest request) {
		cartHelper.populateCartInLayout(customerService, customer.getCustomer(request).getCartId(), model);
		return "customer_home";
	}

	@RequestMapping("/customer/viewProduct")
	public String viewProduct(@RequestParam(value = "category", required = false) String category, Model model,
			HttpServletRequest request) {

		
		if (category != null) {
			cartHelper.populateCartInLayout(customerService, customer.getCustomer(request).getCartId(), model);
			model.addAttribute("category", category);
			model.addAttribute("categorySelected", true);
			model.addAttribute("products", productService.getProductsByCategory(category));

			return "customer_home";
		} else {
			model.addAttribute("invalidCategorySelected", true);
			return "customer_home";
		}
	}

	@RequestMapping("/customer/processProduct")
	public String processProduct(@RequestParam(value = "upc") String upc,
			@RequestParam(value = "category") String category, Model model, HttpServletRequest request) {

		cartHelper.populateCartInLayout(customerService, customer.getCustomer(request).getCartId(), model);
		model.addAttribute("product", productService.getProductByUpc(upc));
		model.addAttribute("category", category);
		model.addAttribute("quantity", customerService.getQuantityOfProductInCart(customer.getCustomer(request).getCartId(), upc));

		return "product";
	}

	@RequestMapping("/customer/productCart")
	public String productCart(@RequestParam(value = "upc") String upc, @RequestParam(value = "quantity") int quantity,
			Model model, HttpServletRequest request) {

		CartProduct product = new CartProduct(productService.getProductByUpc(upc));
		product.setQuantity(quantity);

		String cartId = customer.getCustomer(request).getCartId();

		try {

			customerService.addProductInCart(product, cartId);
			return "sucess";
		} catch (InsufficientStockException e) {
			return "insufficient_stock";
		}
	}
	
	@RequestMapping("/customer/checkout")
	public String checkout(HttpServletRequest request, Model model){
		
		String cartId = customer.getCustomer(request).getCartId();
		
		try {
			customerService.checkout(cartId);
			model.addAttribute("checkoutSuccess", true);
			
			return "customer_home";
		} catch (InsufficientStockException e) {
			return "insufficient_stock";
		}
	}
	
	@RequestMapping("/customer/removeProduct")
	public String removeProduct(@RequestParam(value = "upc") String upc, HttpServletRequest request){
		
		customerService.removeProductInCart(customer.getCustomer(request).getCartId(), upc);
		
		return "customer_home";
	}
}

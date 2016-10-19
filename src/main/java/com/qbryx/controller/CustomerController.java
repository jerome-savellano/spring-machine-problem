package com.qbryx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.Product;
import com.qbryx.exception.InsufficientStockException;
import com.qbryx.helper.CartHelper;
import com.qbryx.helper.UserUtil;
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
		
	@RequestMapping("/customer")
	public String home(Model model, HttpServletRequest request) {
					
		cartHelper.populateCartInLayout(customerService, UserUtil.getUserId(request), model);
		return "customer_home";
	}

	@RequestMapping("/customer/viewProduct")
	public String viewProduct(@RequestParam(value = "category", required = false) String category, Model model,
			HttpServletRequest request) {
		
		cartHelper.populateCartInLayout(customerService, UserUtil.getUserId(request), model);
		
		if (category != null) {
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
		
		Product product = productService.getProductByUpc(upc);
		
		CartProduct cartProduct = customerService.getProductInCart(UserUtil.getUserId(request), upc);
				
		int quantityInCart = 0;
		
		if(cartProduct != null){
			quantityInCart = cartProduct.getQuantity();
		}
		
		cartHelper.populateCartInLayout(customerService, UserUtil.getUserId(request), model);
		
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		model.addAttribute("quantity", quantityInCart);

		return "product";
	}

	@RequestMapping("/customer/addProductToCart")
	public String productCart(@RequestParam(value = "upc") String upc, @RequestParam(value = "quantity") int quantity,
			Model model, HttpServletRequest request) {

		CartProduct cartProduct = new CartProduct();
		cartProduct.setUserId(UserUtil.getUserId(request));
		cartProduct.setProduct(productService.getProductByUpc(upc));
		cartProduct.setQuantity(quantity);
		
		try {

			customerService.addProductInCart(cartProduct);
			return "sucess";
		} catch (InsufficientStockException e) {
			return "insufficient_stock";
		}
	}
	
	@RequestMapping("/customer/checkout")
	public String checkout(HttpServletRequest request, Model model){
		
		long cartId = UserUtil.getUserId(request);
		
		try {
			
			customerService.checkout(cartId);
			model.addAttribute("checkoutSuccess", true);
			
			return "customer_home";
		} catch (InsufficientStockException e) {
			return "insufficient_stock";
		}
	}
	
	@RequestMapping(value="/customer/removeProduct", method=RequestMethod.POST)
	public String removeProduct(@RequestParam(value = "upc") String upc, HttpServletRequest request){
		
		System.out.println(upc);		
		return "customer_home";
	}
}

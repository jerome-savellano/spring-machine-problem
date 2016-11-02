package com.qbryx.controller;

import java.util.List;
import java.util.Map;

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
import com.qbryx.exception.ProductNotFoundException;
import com.qbryx.helper.CartHelper;
import com.qbryx.helper.RejectedProduct;
import com.qbryx.service.CustomerService;
import com.qbryx.service.ManagerService;
import com.qbryx.service.ProductService;
import com.qbryx.util.UserUtil;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "managerService")
	private ManagerService managerService;

	@Resource(name = "cartHelper")
	private CartHelper cartHelper;

	@RequestMapping()
	public String home(Model model, HttpServletRequest request) {

		cartHelper.populateCartInLayout(customerService, UserUtil.getUser(request), model);
		return "customer_home";
	}

	@RequestMapping("/viewProduct")
	public String viewProduct(@RequestParam(value = "category", required = false) String categoryName, Model model,
			HttpServletRequest request) {

		cartHelper.populateCartInLayout(customerService, UserUtil.getUser(request), model);

		if (categoryName != null) {
			List<Product> products = productService.getProductsByCategory(categoryName);

			model.addAttribute("category", categoryName);
			model.addAttribute("products", products);
		}

		return "customer_home";
	}

	@RequestMapping("/processProduct")
	public String processProduct(@RequestParam(value = "upc") String upc, Model model, HttpServletRequest request) {

		Product product = productService.getProduct(upc);

		CartProduct cartProduct = customerService.getProductInCart(UserUtil.getUser(request), product.getId());

		int quantityInCart = 0;

		if (cartProduct != null) {
			quantityInCart = cartProduct.getQuantity();
		}

		cartHelper.populateCartInLayout(customerService, UserUtil.getUser(request), model);

		model.addAttribute("product", product);
		model.addAttribute("category", product.getCategory().getName());
		model.addAttribute("quantity", quantityInCart);

		return "product";
	}

	@RequestMapping("/addProductToCart")
	public String productCart(@RequestParam(value = "upc") String upc, @RequestParam(value = "quantity") int quantity,
			Model model, HttpServletRequest request) {

		CartProduct cartProduct = new CartProduct();
		cartProduct.setUserId(UserUtil.getUser(request).getUserId());
		cartProduct.setProduct(productService.getProduct(upc));
		cartProduct.setQuantity(quantity);

		try {

			customerService.addProductInCart(UserUtil.getUser(request), cartProduct);
			return "success";
		} catch (InsufficientStockException e) {

			return "insufficient_stock";
		}
	}

	@RequestMapping("/checkout")
	public String checkout(HttpServletRequest request, Model model) throws ProductNotFoundException {

		Map<String, List<CartProduct>> checkOutRecord = customerService.checkout(UserUtil.getUser(request));

		List<CartProduct> checkedOutProducts = checkOutRecord.get("checkedOutProducts");

		List<RejectedProduct> rejectedProducts =  RejectedProduct.getRejectedProductsInfo(managerService,
				checkOutRecord.get("rejectedProducts"));

		cartHelper.populateCartInLayout(customerService, UserUtil.getUser(request), model);
		model.addAttribute("checkedOutProducts", checkedOutProducts);
		model.addAttribute("rejectedProducts", rejectedProducts);
		return "customer_home";
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
	public String removeProduct(@RequestParam(value = "id") long id, HttpServletRequest request) {

		CartProduct cartProduct = customerService.getProductInCart(UserUtil.getUser(request), id);

		customerService.removeProductInCart(cartProduct);

		return "redirect:/customer";
	}
}

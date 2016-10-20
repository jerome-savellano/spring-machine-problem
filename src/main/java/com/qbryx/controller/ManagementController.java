package com.qbryx.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.Category;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.service.ManagerService;
import com.qbryx.service.ProductService;
import com.qbryx.util.Path;

@Controller
public class ManagementController {

	@Resource(name = "managerService")
	private ManagerService managerService;

	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping(Path.MANAGEMENT_ROOT_PATH)
	public String management(Model model) {

		model.addAttribute("viewFlag", 1);
		return "management";
	}

	@RequestMapping(Path.MANAGEMENT_ROOT_PATH + "/search")
	public String search(@RequestParam(value = "upc") String upc, Model model) {

		boolean productNotFound = false;
		InventoryProduct product = managerService.getProduct(upc);

		if (product == null) {
			productNotFound = true;
		}

		model.addAttribute("product", product);
		model.addAttribute("productNotFound", productNotFound);
		model.addAttribute("viewFlag", 1);
		return "management";
	}

	@RequestMapping(Path.MANAGEMENT_ROOT_PATH + "/createProduct")
	public String createProduct(Model model, @RequestParam(value = "name") String name,
			@RequestParam(value = "upc") String upc, @RequestParam(value = "category") String _category,
			@RequestParam(value = "description") String description, @RequestParam(value = "price") BigDecimal price,
			@RequestParam(value = "stock") int stock) {

		Category category = productService.getCategory(_category);

		Product product = new Product(upc, category, name, description, price);
		InventoryProduct inventoryProduct = new InventoryProduct(product, stock);

		managerService.addProduct(inventoryProduct);

		model.addAttribute("productCreated", true);
		model.addAttribute("viewFlag", 3);
		return "management";
	}

	@RequestMapping(Path.MANAGEMENT_ROOT_PATH + "/productByCategory")
	public String prodByCat(Model model, @RequestParam(value = "category", required = false) String category) {

		model.addAttribute("viewFlag", 2);

		if (category != null) {

			model.addAttribute("categorySelected", true);
			model.addAttribute("products", productService.getProductsByCategory(category));
		} else {

			model.addAttribute("invalidCategorySelected", true);
		}

		return "management";
	}

	@RequestMapping(Path.MANAGEMENT_ROOT_PATH + "/viewProduct")
	public String viewProduct(Model model, @RequestParam(value = "upc") String upc) {
		InventoryProduct product = managerService.getProduct(upc);
		
		model.addAttribute("product", product);
		return "update_product";
	}
	
	@RequestMapping(Path.MANAGEMENT_ROOT_PATH + "/updateProduct")
	public String updateProduct(Model model, @RequestParam(value = "name") String name,
			@RequestParam(value = "upc") String upc, @RequestParam(value = "category") String _category,
			@RequestParam(value = "description") String description, @RequestParam(value = "price") BigDecimal price,
			@RequestParam(value = "stock") int stock){
		
		Category category = productService.getCategory(_category);

		Product product = new Product(upc, category, name, description, price);
		InventoryProduct inventoryProduct = new InventoryProduct(product, stock);
		
		managerService.updateProduct(inventoryProduct);
	
		model.addAttribute("viewFlag", 2);
		model.addAttribute("productUpdated", true);
		return "management";
	}
}

package com.qbryx.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.DuplicateProductException;
import com.qbryx.exception.ProductNotFoundException;
import com.qbryx.service.ManagerService;
import com.qbryx.service.ProductService;
import com.qbryx.util.InventoryProductHelper;

@Controller
@RequestMapping("/manager")
public class ManagementController {

	@Resource(name = "managerService")
	private ManagerService managerService;

	@Resource(name = "productService")
	private ProductService productService;

	@RequestMapping()
	public String management(Model model) {

		model.addAttribute("activeTab", 1);
		return "management";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(value = "upc") String upc, Model model) {

		InventoryProduct product = null;

		try {

			product = managerService.getProduct(upc);
		} catch (ProductNotFoundException e) {

			model.addAttribute("errorMessage", "Product with UPC " + upc + " does not exist.");
		}

		model.addAttribute("product", product);
		model.addAttribute("activeTab", 1);
		return "management";
	}

	/*
	 * @RequestMapping("/createProduct") public String createProduct(Model
	 * model, @RequestParam(value = "name") String name,
	 * 
	 * @RequestParam(value = "upc") String upc, @RequestParam(value =
	 * "category") String categoryName,
	 * 
	 * @RequestParam(value = "description") String
	 * description, @RequestParam(value = "price") BigDecimal price,
	 * 
	 * @RequestParam(value = "stock") int stock) {
	 * 
	 * model.addAttribute("activeTab", 3);
	 * 
	 * if (Validator.invalidUpcFormat(upc)) {
	 * 
	 * model.addAttribute("invalidFormat", true); return "management"; }
	 * 
	 * Category category = productService.getCategory(categoryName);
	 * 
	 * Product product = new Product(upc, category, name, description, price);
	 * InventoryProduct inventoryProduct = new InventoryProduct(product, stock);
	 * 
	 * try {
	 * 
	 * managerService.add(inventoryProduct);
	 * model.addAttribute("productCreated", true); } catch
	 * (DuplicateProductException e) {
	 * 
	 * model.addAttribute("upc", upc); model.addAttribute("duplicateProduct",
	 * true); }
	 * 
	 * return "management"; }
	 */

	@RequestMapping("/createProduct")
	public String createProduct(
			@ModelAttribute("inventoryProductHelper") @Valid InventoryProductHelper inventoryProductHelper,
			BindingResult bindingResult, Model model) {

		model.addAttribute("activeTab", 3);

		if (bindingResult.hasErrors()) {

			model.addAttribute("errorMessage", bindingResult.getFieldError().getDefaultMessage());
			return "management";
		}

		InventoryProduct inventoryProduct = inventoryProductHelper.buildInventoryProduct(productService);

		try {

			managerService.add(inventoryProduct);
			model.addAttribute("successMessage", "Product successfully created!");
		} catch (DuplicateProductException e) {

			String upc = inventoryProductHelper.getUpc();

			model.addAttribute("errorMessage", "Product with UPC " + upc + " already exists.");
		}

		return "management";
	}

	@RequestMapping("/productByCategory")
	public String prodByCat(Model model, @RequestParam(value = "category", required = false) String category) {

		model.addAttribute("activeTab", 2);

		if (category != null) {
			model.addAttribute("products", productService.getProductsByCategory(category));
		}

		return "management";
	}

	@RequestMapping("/viewProduct")
	public String viewProduct(Model model, @RequestParam(value = "upc") String upc) {

		InventoryProduct product = null;

		try {
			product = managerService.getProduct(upc);
		} catch (ProductNotFoundException e) {

		}

		model.addAttribute("product", product);
		return "update_product";
	}

	/*
	 * @RequestMapping("/updateProduct") public String updateProduct(Model
	 * model, @RequestParam(value = "name") String name,
	 * 
	 * @RequestParam(value = "upc") String upc, @RequestParam(value =
	 * "category") String categoryName,
	 * 
	 * @RequestParam(value = "description") String
	 * description, @RequestParam(value = "price") BigDecimal price,
	 * 
	 * @RequestParam(value = "stock") int stock) {
	 * 
	 * Category category = productService.getCategory(categoryName);
	 * 
	 * Product product = new Product(upc, category, name, description, price);
	 * InventoryProduct inventoryProduct = new InventoryProduct(product, stock);
	 * 
	 * managerService.update(inventoryProduct);
	 * 
	 * model.addAttribute("activeTab", 2); model.addAttribute("productUpdated",
	 * true); return "management"; }
	 */

	@RequestMapping("/updateProduct")
	public String updateProduct(
			@ModelAttribute("inventoryProductHelper") @Valid InventoryProductHelper inventoryProductHelper,
			Model model) {

		InventoryProduct inventoryProduct = inventoryProductHelper.getExistingInvetoryProduct(productService);

		managerService.update(inventoryProduct);

		model.addAttribute("activeTab", 2);
		model.addAttribute("successMessage", "Product successfully updated!");
		return "management";
	}
}

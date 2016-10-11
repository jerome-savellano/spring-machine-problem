package com.qbryx.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.domain.Product;
import com.qbryx.service.ManagerService;

@Controller
public class ManagementController {
	
	@Resource(name = "managerService")
	private ManagerService managerService;

	@RequestMapping("/management")
	public String management(Model model){
		
		model.addAttribute("viewFlag", 1);
		return "management";
	}
	
	@RequestMapping("/management/search")
	public String search(@RequestParam(value="upc") String upc, Model model){
		
		boolean productNotFound = false;
		Product product = managerService.getProductByUpc(upc);
		
		if(product == null){
			productNotFound = true;
		}
		
		model.addAttribute("product", product);
		model.addAttribute("productNotFound", productNotFound);
		model.addAttribute("viewFlag", 1);
		return "management";
	}
}

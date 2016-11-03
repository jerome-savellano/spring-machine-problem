package com.qbryx.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.helper.InventoryProductHelper;

@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> classs) {
		return InventoryProductHelper.class.equals(classs);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", "Name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "upc", "error.upc", "UPC cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "categoryName", "error.categoryName", "Please select a category");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.price", "Your product must have a price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "error.stock", "Your product must have a stock");
	}
	
	@SuppressWarnings("unused")
	private boolean isNumeric(String string){
		return string.matches("[0-9]+");
	}
}

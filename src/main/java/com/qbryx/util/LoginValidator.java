package com.qbryx.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.domain.User;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> classs) {
		return User.class.equals(classs);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "notvalid.username", "Username cannot be empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notvalid.password", "Password cannot be empty!");
	}
}

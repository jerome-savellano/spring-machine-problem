package com.qbryx.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("customer")
@Scope("session")
public class Customer extends User{
	
	@Autowired
	private User user;
	
	private String cartId;
		
	public Customer(User user) {
		super(user.getUserId(), user.getUser_type(), user.getUsername(), user.getPassword());
		// TODO Auto-generated constructor stub
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	public User getUser() {
		return user;
	}

	@Autowired
	public void setUser(User user) {
		this.user = user;
	}
}

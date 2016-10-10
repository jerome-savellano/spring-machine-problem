package com.qbryx.domain;

import javax.servlet.http.HttpServletRequest;

public class Customer extends User{
	
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
	
	public static Customer customer(HttpServletRequest request){
		return (Customer) request.getSession().getAttribute("customer");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

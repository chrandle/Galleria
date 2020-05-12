package com.project.beans;

import java.time.LocalDate;

public class LoginForm {
	private String username;
	private String password;
	private LocalDate date;
	
	
	//	Constructor
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.date = LocalDate.now();
	}

	// ToString
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + ", date=" + date + "]";
	}
	
		
}

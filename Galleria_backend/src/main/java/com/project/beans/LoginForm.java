package com.project.beans;

public class LoginForm {
	private String username;
	private String password;
	
	
	
	//	Constructor
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}

	// Setters and Getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// ToString
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}


		
}

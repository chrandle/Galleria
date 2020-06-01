package com.project.beans;
import static java.util.Collections.emptyList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserBackEndModel extends User{
	private String email;


	public UserBackEndModel(String username, String password, String email) {
		super(username, password, emptyList());
		this.email = email;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

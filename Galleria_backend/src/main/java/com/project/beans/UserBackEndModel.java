package com.project.beans;
import static java.util.Collections.emptyList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserBackEndModel extends User{
	private String email;
	private long userid;
	private String password;


	public UserBackEndModel(String username, String password, String email, long userid) {
		super(username, password, emptyList());
		this.userid = userid;
		this.password = password;
		this.email = email;
	}



	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public long getUserid() {
		return this.userid;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	

}

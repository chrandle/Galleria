package com.project.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import com.project.beans.AppUser;
import com.project.repos.UserRepo;
import com.project.beans.UserBackEndModel;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private UserRepo uRepo;

	public UserDetailServiceImpl(UserRepo uRepo) {
		super();
		this.uRepo = uRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = uRepo.findByUsername(username);
		if (appUser == null) {
			throw new  UsernameNotFoundException(username);
		}
		return new UserBackEndModel(appUser.getUsername(), appUser.getPassword(), appUser.getEmail(), appUser.getUserid());
	}
	
}

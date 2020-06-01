package com.project.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import com.project.beans.AppUser;
import com.project.repos.UserRepo;

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
		return new User(appUser.getUsername(), appUser.getPassword(),emptyList());
	}
	
}

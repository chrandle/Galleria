package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.User;
import com.project.repos.UserRepo;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserRepo userRepo; 
	//TODO: Logging
		@PostMapping("/register")
		public String registerUser(@RequestBody User user) {
			userRepo.save(user);
			return "New User: "+user.toString();
		}
		
		//TODO: restrict to admin role?
		@GetMapping("/all")
		public List<User> getAllUsers() {
			return userRepo.findAll();
		}
		
		
		@GetMapping("/{id}")
		public User userById(@PathVariable long id){
			return userRepo.findById(id).orElse(null);
		}
		
		//TODO: Logging, proper exception<?>
		@DeleteMapping("/delete/{id}")
		public String deleteUser(@PathVariable long id) {
			User checkUser = userRepo.findById(id).orElse(null);
			if (checkUser != null) {
				String toStr = checkUser.toString();
				userRepo.deleteById(id);
				return "Deleted User: " + toStr;
			}
			return "User not Found";
		}
		
//		//TODO: Login and authentication
//		@PostMapping("/login")
//		public void login(@RequestBody login u ) {
//			
//		}
}

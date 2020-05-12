package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import com.project.repos.*;
import com.project.beans.*;

@SpringBootApplication
@RestController
@CrossOrigin(origins ="*")
public class AppDriver {

	@Autowired
	private UserRepo userRepo; 
	
	
	//TODO: Logging
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		userRepo.save(user);
		return "New User: "+user.toString();
	}
	
	//TODO: restrict to admin role?
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	
	@GetMapping("/user/{id}")
	public User userById(@PathVariable long id){
		return userRepo.findById(id).orElse(null);
	}
	
	//TODO: Logging, proper exception<?>
	@DeleteMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		User checkUser = userRepo.findById(id).orElse(null);
		if (checkUser != null) {
			String toStr = checkUser.toString();
			userRepo.deleteById(id);
			return "Deleted User: " + toStr;
		}
		return "User not Found";
	}
	
//	//TODO: Login and authentication
//	@PostMapping("/login")
//	public void login(@RequestBody login u ) {
//		
//	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppDriver.class, args);
	}
	
	
	
	
	
}

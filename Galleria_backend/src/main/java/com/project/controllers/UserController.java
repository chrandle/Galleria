package com.project.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.beans.LoginForm;
import com.project.beans.AppUser;
import com.project.repos.UserRepo;
import com.project.security.JWTAuthenticationFilter;
import com.project.security.JWTAuthorizationFilter;
import com.project.services.UserDetailServiceImpl;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserRepo userRepo;

	private BCryptPasswordEncoder passwordEncoder;
	
//	// For logging
//	private LocalDate currentDate = LocalDate.now();

	
	public UserController(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	//TODO: Logging
	
	// new user registration
		@PostMapping("/register")
		public ResponseEntity<String> registerUser (@RequestBody AppUser appUser) {
			
			// Encrypt password via spring security
			appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
			userRepo.save(appUser);
			
			try {
				return ResponseEntity.ok().body("New User: "+appUser.toString());
			} catch (Exception e) {
				return ResponseEntity.status(500).body("Server error: "+e.getMessage());
			}

		}
		
		// update user
		@PostMapping("/update/{id}")
		public ResponseEntity<String> registerUser (@PathVariable long id,@RequestBody AppUser newUser) {
			
			AppUser oldUser = userRepo.getOne(id);
			if (newUser.getUsername()!="") {
				oldUser.setUsername(newUser.getUsername());
			}
			
			if (newUser.getPassword()!="") {
					oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			}
			
			if (newUser.getEmail()!="") {
					oldUser.setEmail(newUser.getEmail());
			}
			
			userRepo.save(oldUser);
			
			
			try {
				return ResponseEntity.ok().body("Updated User: "+oldUser.toString());
			} catch (Exception e) {
				return ResponseEntity.status(500).body("Server error: "+e.getMessage());
			}

		}
		


		// Used for testing
		@GetMapping("/all")
		public ResponseEntity<List<AppUser>> getAllUsers() {
			List<AppUser> retval = userRepo.findAll();
			
			
			try {
					if (retval.size() != 0) {
						return ResponseEntity.ok().body(retval);
					} else {
						return ResponseEntity.status(404).body(null);
					}
				
			} catch (Exception e) {
				return ResponseEntity.status(500).body(null);
			}
			
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<AppUser> userById(@PathVariable long id){
			AppUser retval = userRepo.findById(id).orElse(null);
			
			try {
					if (retval !=  null) {
						return ResponseEntity.ok().body(retval);
					} else {
						return ResponseEntity.status(404).body(null);
					}

			} catch (Exception e) {
				return ResponseEntity.status(500).body(null);
			}
		}
		
		//TODO: Logging, proper exception<?>
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable long id) {
			try {
				AppUser checkUser = userRepo.findById(id).orElse(null);
					if (checkUser != null) {
						userRepo.deleteById(id);
						return ResponseEntity.ok().body("Deleted User: " + checkUser.getUsername());
					}else {
						return ResponseEntity.status(404).body(null);
					}
			} catch (Exception e) {
				return ResponseEntity.status(500).body("Server error: "+e.getMessage());
			}
		}
		

}

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
import com.project.beans.User;
import com.project.repos.UserRepo;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	private  BCryptPasswordEncoder passwordEncoder;
	
//	// For logging
//	private LocalDate currentDate = LocalDate.now();

	
	//TODO: Logging
		@PostMapping("/register")
		public ResponseEntity<String> registerUser (@RequestBody User user) {
			
			// Encrypt password via spring security
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			
			try {
				return ResponseEntity.ok().body("New User: "+user.toString());
			} catch (Exception e) {
				return ResponseEntity.status(500).body("Server error: "+e.getMessage());
			}

		}
		
		// Used for testing
		@GetMapping("/all")
		public ResponseEntity<List<User>> getAllUsers() {
			List<User> retval = userRepo.findAll();
			
			
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
		public ResponseEntity<User> userById(@PathVariable long id){
			User retval = userRepo.findById(id).orElse(null);
			
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
				User checkUser = userRepo.findById(id).orElse(null);
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
		
//		//TODO: Login and authentication
//		@PostMapping("/authenticate")
//		public void login(@RequestBody LoginForm login ) {
//			String username = login.getUsername();
//			String password = login.getPassword();
//			
//			
//			
//		}
}

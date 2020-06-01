package com.project.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.AppUser;

public interface UserRepo extends JpaRepository<AppUser,Long> {

	AppUser findByUsername(String username);	

}

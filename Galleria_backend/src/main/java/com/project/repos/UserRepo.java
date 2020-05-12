package com.project.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.User;

public interface UserRepo extends JpaRepository<User,Long> {

	

}

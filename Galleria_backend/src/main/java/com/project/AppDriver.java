package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import com.project.repos.*;
import com.project.beans.*;

@SpringBootApplication
public class AppDriver {

	public static void main(String[] args) {
		SpringApplication.run(AppDriver.class, args);
	}
	
	
	
	
	
}

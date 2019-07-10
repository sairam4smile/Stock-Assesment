package com.share.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share.model.Userdeatils;
import com.share.service.IUserservice;
import com.share.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired IUserservice iuserService;
	
	@PostMapping("/registration")
	public ResponseEntity<String> userRegistration( @RequestBody Userdeatils userdeatils) {
		return iuserService.userRegistration(userdeatils);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin( @RequestParam String username, @RequestParam String password) {
		return iuserService.login(username, password);
		
	}
	
	

}

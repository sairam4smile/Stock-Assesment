package com.share.service;

import org.springframework.http.ResponseEntity;

import com.share.model.Userdeatils;

public interface IUserservice {
	
	public ResponseEntity<String> userRegistration(Userdeatils userdeatils);
	
	public ResponseEntity<String> login(String Username, String password);


}

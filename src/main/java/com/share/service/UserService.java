package com.share.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.share.exception.StockException;
import com.share.model.Userdeatils;
import com.share.repository.UserDeatilsRepository;

@Service
public class UserService implements IUserservice {
	
	@Autowired UserDeatilsRepository userDeatilsRepository;
	
	
	public ResponseEntity<String> userRegistration(Userdeatils userdeatils) {
	
		if(!userDeatilsRepository.findByUserName(userdeatils.getUserName()).isEmpty())
		  throw new StockException(userdeatils.getUserName()+" is already existed");
		else
			return new ResponseEntity<>( userDeatilsRepository.save(userdeatils).getUserName()+" is succsessfully registerd",HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<String> login(String userName, String password) {
		 if(userDeatilsRepository.findByUserNameAndPassword(userName, password).isEmpty())
			 throw new StockException("user credintials are incorrect");
		 
		 return new ResponseEntity<>(userName+" succsessfully logined",HttpStatus.OK);
		 
	}

}

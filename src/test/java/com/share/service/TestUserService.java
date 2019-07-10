package com.share.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.share.exception.StockException;
import com.share.model.Userdeatils;
import com.share.repository.UserDeatilsRepository;


@RunWith(SpringRunner.class)
public class TestUserService {

	@Mock
	UserDeatilsRepository userDeatilsRepository;

	@InjectMocks
	UserService userService;
	
	Userdeatils userdeatils;
	
	List<Userdeatils> userdeatilsl;

	
	@Before
	public void init() {
		
		userdeatils=new Userdeatils();
		
		userdeatils.setUserId(1L);
		userdeatils.setPassword("1234");
		userdeatils.setUserName("sairam");
		
		userdeatilsl=new ArrayList<>();
		userdeatilsl.add(userdeatils);
	}
	
	@Test
	public void TestuserRegistration() {
		Mockito.when(userDeatilsRepository.findByUserName("hari")).thenReturn(userdeatilsl);
		Mockito.when(userDeatilsRepository.save(userdeatils)).thenReturn(userdeatils);
		
		ResponseEntity<String> expectedValue = userService.userRegistration(userdeatils);
		Assert.assertEquals(expectedValue.getBody(), "sairam is succsessfully registerd");
	}
	
	@Test(expected=StockException.class)
	public void TestuserRegistrationException() {
		Mockito.when(userDeatilsRepository.findByUserName(userdeatils.getUserName())).thenReturn(userdeatilsl);
		Mockito.when(userDeatilsRepository.save(userdeatils)).thenReturn(userdeatils);
		
		ResponseEntity<String> expectedValue = userService.userRegistration(userdeatils);
	}

	
	@Test(expected=StockException.class)
	public void TestLoginException() {
		userdeatilsl=new ArrayList<>();
		Mockito.when(userDeatilsRepository.findByUserNameAndPassword("user1",userdeatils.getPassword())).thenReturn(userdeatilsl);
		
		ResponseEntity<String> expectedValue = userService.login("user1",userdeatils.getPassword());
		Assert.assertEquals(expectedValue.getBody(), "sairam succsessfully logined");

	
	}
	
	@Test
	public void TestLogin() {
		Mockito.when(userDeatilsRepository.findByUserNameAndPassword(userdeatils.getUserName(),userdeatils.getPassword())).thenReturn(userdeatilsl);
		
		ResponseEntity<String> expectedValue = userService.login(userdeatils.getUserName(),userdeatils.getPassword());
		Assert.assertEquals(expectedValue.getBody(), "sairam succsessfully logined");

	
	}

}

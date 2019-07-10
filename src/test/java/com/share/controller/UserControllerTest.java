package com.share.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.share.model.Userdeatils;
import com.share.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserControllerTest {
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;
	
	Userdeatils userdeatils;
	
	@Before
	public void setUp() {
		
		mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
		userdeatils=new Userdeatils();
	}
	
	@Test
	public void userRegistration() throws Exception {
		

		 mockMvc.perform(MockMvcRequestBuilders.post("/user/registration").contentType(MediaType.APPLICATION_JSON)
	 				.accept(MediaType.ALL).content(asJsonString(userdeatils))).andExpect(status().isOk());

		
		
	}
	
	@Test
	public void userLong() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login?password=12344&username=sairam").contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andExpect(status().isOk());
	
	}
	
	
	 public static String asJsonString(final Object obj) {
	 		try {
	 			return new ObjectMapper().writeValueAsString(obj);
	 		} catch (Exception e) {
	 			throw new RuntimeException(e);
	 		}
	 	}

}

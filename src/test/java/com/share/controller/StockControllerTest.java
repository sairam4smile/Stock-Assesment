package com.share.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.share.model.Cart;
import com.share.model.Stock;
import com.share.service.StockService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class StockControllerTest {


	@Mock
	StockService stockService;
	
	@InjectMocks
	StockController stockController;

	private MockMvc mockMvc;
	
	Stock stock;
	Cart cart;
	
	@Before
	public void setUp() {
		
		mockMvc=MockMvcBuilders.standaloneSetup(stockController).build();
		stock=new Stock();
		cart=new Cart();
	}
	
	
	@Test
	public void addStock() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/stock/add").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(stock))).andExpect(status().isOk());

		
	}
	
	
	@Test
	     public void addCart() throws Exception {
			
	    	 mockMvc.perform(MockMvcRequestBuilders.post("/stock/cart?cartType=add").contentType(MediaType.APPLICATION_JSON)
	 				.accept(MediaType.ALL).content(asJsonString(cart))).andExpect(status().isOk());

		}
	
	@Test
     public void buyStock() throws Exception {
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/stock/buy?userId=1").contentType(MediaType.APPLICATION_JSON)
	 				.accept(MediaType.ALL).content(asJsonString(cart))).andExpect(status().isOk());

		
		
	}
//	
     @Test
     public void getStocks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stock/stocks").contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andExpect(status().isOk());

	}
	
	
     @Test
    public void sharePrice() throws Exception {
    	 
    	 mockMvc.perform(MockMvcRequestBuilders.get("/stock/stocks/price/{shareId}",1).contentType(MediaType.APPLICATION_JSON)
	 				.accept(MediaType.ALL).content(asJsonString(cart))).andExpect(status().isOk());

		
	}
     
     public static String asJsonString(final Object obj) {
 		try {
 			return new ObjectMapper().writeValueAsString(obj);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 	}

	
}

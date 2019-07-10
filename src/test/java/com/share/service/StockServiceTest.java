package com.share.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.share.exception.StockException;
import com.share.model.Cart;
import com.share.model.SaledDetails;
import com.share.model.Stock;
import com.share.model.Userdeatils;
import com.share.repository.CartRepository;
import com.share.repository.SaledDetailsRepository;
import com.share.repository.StockRepository;
import com.share.repository.UserDeatilsRepository;

@RunWith(SpringRunner.class)
public class StockServiceTest {
	
	@Mock
	StockRepository stockRepository;
	
	@Mock
	CartRepository cartRepository;
	
	@Mock
	SaledDetailsRepository saledDetailsRepository;
	
	@InjectMocks
	StockService stockService;
	
	
	
	Stock stock;
	Cart cart;
	Userdeatils userdeatils;
	SaledDetails salesDetails;
	
	List<Userdeatils> userdeatilsl;
	List<Cart> cartl;
	
	List<Stock> stockl;
	
	@Before
	public void init() {
		
		stock=new Stock();
		stock.setCompanyName("hcl");
		stock.setPrice(10d);
		stock.setSharesCount(100);
		stock.setStockId(1l);
		
		stockl=new ArrayList<>();
		stockl.add(stock);
		
		
		cart=new Cart();
		cart.setCartId(2L);
		cart.setPrice(10d);
		cart.setSharesCount(3);
		cart.setStockId(1L);
		cart.setUserId(3L);
		
		
		cartl=new ArrayList<>();
		cartl.add(cart);
		
		userdeatils=new Userdeatils();
		userdeatils.setPassword("1234");
	userdeatils.setUserName("sai");
	userdeatils.setUserId(4L);
	
	userdeatilsl=new ArrayList<>();
	userdeatilsl.add(userdeatils);
		
		
	}
	
	@Test
	public void addStockTest() {
		
		Mockito.when(stockRepository.save(stock)).thenReturn(stock);
		
		ResponseEntity<String> expectedValue = stockService.addStock(stock);
		Assert.assertEquals(expectedValue.getBody(), "hcl stocks are added succsesfully");

	}
	
	@Test
	public void addCartTest() {
		
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);
		
		ResponseEntity<String> expectedValue = stockService.addCart(cart, "add");
		Assert.assertEquals(expectedValue.getBody(), "1added succsessfully");

	}
	
	@Test
	public void addCartTestDelete() {
		
		Mockito.when(cartRepository.findByUserIdAndStockId(cart.getUserId(), cart.getStockId())).thenReturn(cartl);
//		Mockito.when(cartRepository.deleteById(2L))

		ResponseEntity<String> expectedValue = stockService.addCart(cart, "remove");
		Assert.assertEquals(expectedValue.getBody(), "1 removed successfully");

	}
	
	
	@Test(expected=StockException.class)
	public void addCartTestException() {
		
		Mockito.when(cartRepository.findByUserIdAndStockId(cart.getUserId(), cart.getStockId())).thenReturn(cartl);
//		Mockito.when(cartRepository.deleteById(2L))

		ResponseEntity<String> expectedValue = stockService.addCart(cart, "llll");
		Assert.assertEquals(expectedValue.getBody(), "1 removed successfully");

	}
	
	@Test(expected=StockException.class)
	public void BuyStockTestException() {
		Mockito.when(cartRepository.findByUserId(cart.getCartId())).thenReturn(cartl);
		Mockito.when(saledDetailsRepository.save(salesDetails)).thenReturn(salesDetails);
		
//		Mockito.when(cartRepository.deleteById(cart.getCartId()));
		Mockito.when(cartRepository.findByUserIdAndStockId(cart.getUserId(), cart.getStockId())).thenReturn(cartl);

		ResponseEntity<String> expectedValue = stockService.buyStocks(4l);
		Assert.assertEquals(expectedValue.getBody(), "purchesed succsessfully");

	}
	
	
	@Test
	public void BuyStockTest() {
		Mockito.when(cartRepository.findByUserId(userdeatils.getUserId())).thenReturn(cartl);
		Mockito.when(saledDetailsRepository.save(salesDetails)).thenReturn(salesDetails);
		
//		Mockito.when(cartRepository.deleteById(cart.getCartId()));
		Mockito.when(cartRepository.findByUserIdAndStockId(cart.getUserId(), cart.getStockId())).thenReturn(cartl);

		ResponseEntity<String> expectedValue = stockService.buyStocks(userdeatils.getUserId());
		Assert.assertEquals(expectedValue.getBody(), "purchesed succsessfully");

	}
	
	@Test
	public void getStockTest() {
		Mockito.when(stockRepository.findAll()).thenReturn(stockl);
		

		List<Stock> expectedValue = stockService.getStocks();
		Assert.assertEquals(expectedValue, stockl);

	}

	
	@Test
	public void getStockPriceTest() {
		Mockito.when(stockRepository.findById(stock.getStockId())).thenReturn(Optional.of(stock));
		

		Double expectedValue = stockService.getPrice(stock.getStockId());
		Assert.assertEquals(expectedValue, stock.getPrice());

	}

	
	

}

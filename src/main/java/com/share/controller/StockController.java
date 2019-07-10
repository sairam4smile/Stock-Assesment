package com.share.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share.model.Cart;
import com.share.model.Stock;
import com.share.service.IStockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	
	@Autowired IStockService iStockService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addStock( @RequestBody Stock stock) {
		return iStockService.addStock(stock);
		
	}
	
	
	//add cart
		@PostMapping("/cart")
	     public ResponseEntity<String> addCart(@RequestBody Cart cart,@RequestParam String cartType) {
			
			return iStockService.addCart(cart, cartType);
			
		}
	
	//from cart
	@PostMapping("/buy")
     public ResponseEntity<String> buyStock(@RequestParam Long userId) {
		
		return iStockService.buyStocks(userId);
		
	}
	
	@GetMapping("/stocks")
    public List<Stock> getStocks() {
		
		return iStockService.getStocks();
	}
	
	
	@GetMapping("/stocks/price/{shareId}")
    public Double sharePrice(@PathVariable("shareId") Long shareId) {
		
		return iStockService.getPrice(shareId);
	}
	

}

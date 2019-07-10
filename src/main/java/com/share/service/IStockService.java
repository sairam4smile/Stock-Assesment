package com.share.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.share.model.Cart;
import com.share.model.Stock;

public interface IStockService {
	
	public ResponseEntity<String> addStock(Stock stock);
	public ResponseEntity<String> addCart(Cart cart,String cartType);
	public ResponseEntity<String> buyStocks(Long userId);
	public List<Stock> getStocks();
	public Double getPrice(Long ShareId);



}

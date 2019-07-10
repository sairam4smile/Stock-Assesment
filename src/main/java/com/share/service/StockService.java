package com.share.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.share.exception.StockException;
import com.share.model.Cart;
import com.share.model.EcartType;
import com.share.model.SaledDetails;
import com.share.model.Stock;
import com.share.repository.CartRepository;
import com.share.repository.SaledDetailsRepository;
import com.share.repository.StockRepository;

@Service
public class StockService implements IStockService {

	@Autowired StockRepository stockRepository;
	@Autowired CartRepository  cartRepository;
	@Autowired SaledDetailsRepository saledDetailsRepository;

	@Override
	public ResponseEntity<String> addStock(Stock stock) {
		
		return new ResponseEntity<>(stockRepository.save(stock).getCompanyName()+" stocks are added succsesfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addCart(Cart cart, String cartType) {
		
	
		if(cartType.equalsIgnoreCase((EcartType.ADD.toString()))) {
			
//			if((cartRepository.countStocks(cart.getUserId())+saledDetailsRepository.countStocks(cart.getUserId())>5) ||( cartRepository.countAmount(cart.getUserId())+saledDetailsRepository.countAmount(cart.getUserId())>250) ) {
//				throw  new StockException("Limit exceded");
//			}
			
		 return new  ResponseEntity<>(cartRepository.save(cart).getStockId()+"added succsessfully",HttpStatus.OK);
			
		
		
		}else if(cartType.equalsIgnoreCase((EcartType.REMOVE.toString()))) {
			 
			cartRepository.deleteById(cartRepository.findByUserIdAndStockId(cart.getUserId(), cart.getStockId()).get(0).getCartId());
		return new ResponseEntity<>(cart.getStockId()+" removed successfully",HttpStatus.OK);
		
		}else {
			throw new StockException("inavlid cart operation");
		}
		
		
	}

	@Override
	public ResponseEntity<String> buyStocks(Long userId) {
		
		List<Cart> cartExist = cartRepository.findByUserId(userId);
		if(cartExist.isEmpty())
			throw new StockException(" no cart existed");
		for(Cart cart: cartExist) {
			
			SaledDetails salesdetails=new SaledDetails();
			
			salesdetails.setUserId(cart.getUserId());
			salesdetails.setPrice(cart.getPrice());
			salesdetails.setSharesCount(cart.getSharesCount());
			salesdetails.setStockId(cart.getStockId());
			salesdetails.setDate(new Date());
			
			saledDetailsRepository.save(salesdetails);
			
			cartRepository.deleteById(cart.getCartId());
		}
		
		return new ResponseEntity<>("purchesed succsessfully",HttpStatus.OK);
	}

	@Override
	public List<Stock>getStocks() {
		return stockRepository.findAll();
	}

	@Override
	public Double getPrice(Long ShareId) {
		return stockRepository.findById(ShareId).get().getPrice();
	}

	

	

}

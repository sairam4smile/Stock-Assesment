package com.share.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.share.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  {
	public List<Cart> findByUserIdAndStockId(Long userId, Long stockId);
	
	public List<Cart> findByUserId(Long userId);

	@Query(value="select sum(price) from cart c where c.user_id=?1",nativeQuery=true)
	public Integer countStocks(Long userid);
	
	@Query(value="select sum(shares_count) from cart c where c.user_id=?1",nativeQuery=true)
	public Integer countAmount(Long userid);
	

}

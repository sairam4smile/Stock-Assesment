package com.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.share.model.SaledDetails;

@Repository
public interface SaledDetailsRepository  extends JpaRepository<SaledDetails, Long>{
	
	
	@Query(value="select sum(price) from saled_deatils c where c.user_id=?1",nativeQuery=true)
	public Integer countStocks(Long userid);
	
	@Query(value="select sum(shares_count) from saled_deatils c where c.user_id=?1",nativeQuery=true)
	public Integer countAmount(Long userid);

}

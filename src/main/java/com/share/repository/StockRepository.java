package com.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.share.model.Stock;
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}

package com.share.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STOCK")
@Data
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long stockId;
	private String CompanyName;
	private int SharesCount;
	private Double price;
	
	

}

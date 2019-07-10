package com.share.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CART")
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cartId;
	private int sharesCount;
	private Double price;
	private Long userId;
	private Long stockId;


}

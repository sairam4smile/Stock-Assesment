package com.share.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="SALED_DEATILS")
@Data
public class SaledDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long saledId;
	
	private Date date;
	private int sharesCount;
	private Double price;
	private Long userId;
	private Long stockId;

}

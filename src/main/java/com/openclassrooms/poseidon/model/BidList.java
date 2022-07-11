package com.openclassrooms.poseidon.model;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "bidlist")
public class BidList {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int  BidListId;
	 
	    private String account;

	    private String type;
	   
	    private Double bidQuantity;
	    private Double askQuantity;
	    private Double bid;
	    private Double ask;
	    private String benchmark;
	    private Timestamp bidListDate;
	    private String commentary;
	    private String security;
	    private String status;
	    private String trader;
	    private String book;
	    private String creationName;
	    private Timestamp creationDate;
	    private String revisionName;
	    private Timestamp revisionDate;
	    private String dealName;
	    private String dealType;
	    private String sourceListId;
	    private String side;


	    public BidList(String account, String type, double bidQuantity) {
	            this.account = account;
	            this.type = type;
	            this.bidQuantity = bidQuantity;
	    }


		public BidList() {
			super();
			
		}
}

package com.openclassrooms.poseidon.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private int  tradeId;

	    private String account;
	    private String type;
	    private double buyQuantity;
	    private double sellQuantity;
	    private double buyPrice;
	    private double sellPrice;
	    private String benchmark;
	    private Timestamp tradeDate;
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

	    public Trade(String account, String type, double buyQuantity ) {
	        this.type = type;
	        this.account = account;
	        this.buyQuantity = buyQuantity;
	    }
}

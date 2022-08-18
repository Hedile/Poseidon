package com.openclassrooms.poseidon.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TradeId")
	private Integer id;
	@Column(name = "account")
	@NotEmpty(message = "Account is mandatory")
	private String account;
	@Column(name = "type")
	@NotEmpty(message = "Type is mandatory")
	private String type;
	@Column(name = "buyQuantity")
	@NotNull(message = "Buy quantity is mandatory")
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

	public Trade(String account, String type, double buyQuantity) {
		this.type = type;
		this.account = account;
		this.buyQuantity = buyQuantity;
	}

	public Trade() {
		super();
		
	}

}

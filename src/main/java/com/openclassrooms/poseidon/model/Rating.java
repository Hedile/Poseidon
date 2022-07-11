package com.openclassrooms.poseidon.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "rating")
public class Rating {
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private int id;

	    private String moodysRating;
	    private String sandPRating;
	    private String fitchRating;
	    private int orderNumber;

	    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
	        this.moodysRating = moodysRating;
	        this.sandPRating = sandPRating;
	        this.fitchRating = fitchRating;
	        this.orderNumber = orderNumber;
	    }
	
}

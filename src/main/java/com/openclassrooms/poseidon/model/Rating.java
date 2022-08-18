package com.openclassrooms.poseidon.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "moodysRating")
	@NotBlank(message = "Moodys Rating is mandatory")
	private String moodysRating;
	@Column(name = "sandPRating")
	@NotBlank(message = "Sand Rating is mandatory")
	private String sandPRating;
	@Column(name = "fitchRating")
	@NotBlank(message = "Fitch Rating is mandatory")
	private String fitchRating;
	@Column(name = "orderNumber")
	@NotNull(message = "Order Number is mandatory")
	@Min(value = 1, message = "Order Number must be greater than 0")
	private Integer orderNumber;

	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	public Rating() {
		super();
	}

}

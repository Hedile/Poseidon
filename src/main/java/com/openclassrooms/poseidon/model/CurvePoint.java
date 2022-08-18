package com.openclassrooms.poseidon.model;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private  Integer  id;
	
	@Column(name = "curveId")
	@Min(value = 1, message = "Curve Id must be greater than 0")
	@NotNull(message = "Curve Id is mandatory")
	private Integer curveId;
	
	@Column(name = "asOfDate")
	private Timestamp asOfDate;
	
	@Column(name = "term")
	@Min(value = 1, message = "Term must be greater than 0")
	@NotNull(message = "Term is mandatory")
	private Double term;
	
	@Column(name = "value")
	@Min(value = 1, message = "Value must be greater than 0")
	@NotNull(message = "Value is mandatory")
	private Double value;
	
	@Column(name = "creationDate")
	private Timestamp creationDate;

	public CurvePoint(Integer curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	public CurvePoint() {
		super();

	}

}

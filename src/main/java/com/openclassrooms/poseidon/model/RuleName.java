package com.openclassrooms.poseidon.model;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	@NotEmpty(message = "Name is mandatory")
	private String name;
	@Column(name = "description")
	@NotEmpty(message = "Description is mandatory")
	private String description;
	@Column(name = "json")
	@NotEmpty(message = "Json is mandatory")
	private String json;
	@Column(name = "template")
	@NotEmpty(message = "Template is mandatory")
	private String template;
	@Column(name = "sqlStr")
	@NotEmpty(message = "Sql STR is mandatory")
	private String sqlStr;
	@Column(name = "sqlPart")
	@NotEmpty(message = "Sql Part is mandatory")
	private String sqlPart;

	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	public RuleName() {
		super();
		// TODO Auto-generated constructor stub
	}

}

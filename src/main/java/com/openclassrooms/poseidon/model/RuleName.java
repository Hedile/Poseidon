package com.openclassrooms.poseidon.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "rulename")
public class RuleName {
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private int id;

	    private String name;
	    private String description;
	    private String json;
	    private String template;
	    private String sqlStr;
	    private String sqlPart;

	    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
	        this.name = name;
	        this.description = description;
	        this.json = json;
	        this.template = template;
	        this.sqlStr = sqlStr;
	        this.sqlPart = sqlPart;
	     }
}

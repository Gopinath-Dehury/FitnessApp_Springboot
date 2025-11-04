package com.fitnessApp.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Goal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer calToBeBurn;
	private Integer netCalBurn;
	@ManyToOne(cascade = CascadeType.ALL)
	private Users user;
	
	
}

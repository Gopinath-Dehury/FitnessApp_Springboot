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
public class Workout {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String type;
	private String duration;
	@ManyToOne(cascade = CascadeType.ALL)
	private Users user;
}

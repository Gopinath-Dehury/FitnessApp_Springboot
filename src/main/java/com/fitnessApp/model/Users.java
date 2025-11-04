package com.fitnessApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Users {

	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private Integer age;
	@NotNull
	private String mailid;
	
}

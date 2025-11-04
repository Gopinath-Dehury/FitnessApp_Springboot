package com.fitnessApp.DTO;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Username cannot be blank")
	private String username;
    private String firstName;
	private String lastName;
    @NotNull(message = "Password cannot be null")
	private String password;
    @NotNull(message = "Age cannot be null")
	private Integer age;
    @UniqueElements
	private String mailid;
}

package com.example.demo.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegistrationForm {
	
	private String firstName;
	private String lastName;
	private String email;
	private String pswd;
	private Long mobile;
	private LocalDate dob;
	private String gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countrId;

}

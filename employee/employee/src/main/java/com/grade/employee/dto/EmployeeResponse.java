package com.grade.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponse {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
}

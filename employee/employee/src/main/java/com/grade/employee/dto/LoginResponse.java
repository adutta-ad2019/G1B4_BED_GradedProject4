package com.grade.employee.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String username;
	private String password;
	private List<RoleDto> roles;
}

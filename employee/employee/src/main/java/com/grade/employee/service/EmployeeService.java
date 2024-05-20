package com.grade.employee.service;

import java.util.List;

import com.grade.employee.dto.EmployeeResponse;
import com.grade.employee.dto.LoginRequest;
import com.grade.employee.dto.LoginResponse;
import com.grade.employee.entity.Employee;

public interface EmployeeService {
	
	EmployeeResponse getEmployeeById(Integer id);
	List<EmployeeResponse> getEmployeesByFirstname(String firstname);
	List<EmployeeResponse> getAllEmployees();
	List<EmployeeResponse> getAllEmployeesSorting(String order);
	EmployeeResponse addEmployee(Employee employee);
	EmployeeResponse updateEmployee(Employee employee);
	LoginResponse loginEmployee(LoginRequest loginRequest);
	String deleteEmployee(Integer id);

}

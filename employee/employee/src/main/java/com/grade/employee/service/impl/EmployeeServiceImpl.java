package com.grade.employee.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.employee.dto.EmployeeResponse;
import com.grade.employee.dto.LoginRequest;
import com.grade.employee.dto.LoginResponse;
import com.grade.employee.dto.RoleDto;
import com.grade.employee.entity.Employee;
import com.grade.employee.entity.Roles;
import com.grade.employee.exception.EmployeeNotFoundException;
import com.grade.employee.repository.EmployeeRepository;
import com.grade.employee.repository.RolesRepository;
import com.grade.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	RolesRepository rolesRepo;

	@Override
	public EmployeeResponse getEmployeeById(Integer id) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not found with id: " + id));
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setEmail(employee.getEmail());
		return response;
	}

	@Override
	public List<EmployeeResponse> getEmployeesByFirstname(String firstname) {
		List<Employee> employees = employeeRepo.findByFirstName(firstname)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not found with first name: " + firstname));
		List<EmployeeResponse> res = employees.stream()
				.map(emp -> {
					EmployeeResponse response = new EmployeeResponse();
					response.setFirstName(emp.getFirstName());
					response.setLastName(emp.getLastName());
					response.setEmail(emp.getEmail());
					response.setId(emp.getId());
					return response;
					})
				.collect(Collectors.toList());
		return res;
	}

	@Override
	public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		List<EmployeeResponse> res = employees.stream()
				.map(emp -> {
					EmployeeResponse response = new EmployeeResponse();
					response.setFirstName(emp.getFirstName());
					response.setLastName(emp.getLastName());
					response.setEmail(emp.getEmail());
					response.setId(emp.getId());
					return response;
					})
				.collect(Collectors.toList());
		return res;
	}
	
	@Override
	public List<EmployeeResponse> getAllEmployeesSorting(String order) {
		List<Employee> employees = null;
		if ("ASC".equalsIgnoreCase(order)) {
			employees = employeeRepo.findAllByFirstnameAsc()
					.orElseThrow(() -> new EmployeeNotFoundException("Invalid sorting order given: " + order + "Valid order: ASC or DESC"));
		} else if ("DESC".equalsIgnoreCase(order)) {
			employees = employeeRepo.findAllByFirstnameDesc()
					.orElseThrow(() -> new EmployeeNotFoundException("Invalid sorting order given: " + order + "Valid order: ASC or DESC"));
		}
		
		List<EmployeeResponse> res = employees.stream()
				.map(emp -> {
					EmployeeResponse response = new EmployeeResponse();
					response.setFirstName(emp.getFirstName());
					response.setLastName(emp.getLastName());
					response.setEmail(emp.getEmail());
					response.setId(emp.getId());
					return response;
					})
				.collect(Collectors.toList());
		return res;
	}

	@Override
	public EmployeeResponse addEmployee(Employee emp) {
		Roles role = rolesRepo.findById(2).get();
		emp.setRoles(Collections.singletonList(role));
		Employee employee = employeeRepo.save(emp);
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setEmail(employee.getEmail());
		return response;
	}

	@Override
	public EmployeeResponse updateEmployee(Employee employeeReq) {
		Employee updateEmployee = employeeRepo.findById(employeeReq.getId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not found with id: " + employeeReq.getId()));
		updateEmployee.setEmail(employeeReq.getEmail());
		updateEmployee.setFirstName(employeeReq.getFirstName());
		updateEmployee.setLastName(employeeReq.getLastName());
		Employee employee = employeeRepo.save(updateEmployee);
		EmployeeResponse response = new EmployeeResponse();
		response.setId(employee.getId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setEmail(employee.getEmail());
		return response;
	}

	@Override
	public LoginResponse loginEmployee(LoginRequest loginRequest) {
		Employee employee = employeeRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not found with username: " + loginRequest.getUsername() 
				+ " or password: " + loginRequest.getPassword()));
		LoginResponse response = new LoginResponse();
		response.setUsername(employee.getUsername());
		response.setPassword(employee.getPassword());
		List<RoleDto> roleList = employee.getRoles().stream()
		.map(role -> {
			RoleDto roleDto = new RoleDto();
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());
			return roleDto;
		}).collect(Collectors.toList());
		response.setRoles(roleList);
		return response;
	}

	@Override
	public String deleteEmployee(Integer id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not found with id: " + id));
		employeeRepo.delete(employee);
		return "Deleted employee id - " + id;
	}

}

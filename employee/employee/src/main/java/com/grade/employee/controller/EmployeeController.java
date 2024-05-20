package com.grade.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grade.employee.dto.EmployeeResponse;
import com.grade.employee.dto.LoginRequest;
import com.grade.employee.dto.LoginResponse;
import com.grade.employee.entity.Employee;
import com.grade.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") Integer id) {
		EmployeeResponse employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@GetMapping("/search/{firstname}")
	public ResponseEntity<List<EmployeeResponse>> getEmployeesByFirstname(@PathVariable("firstname") String firstname) {
		List<EmployeeResponse> employees = employeeService.getEmployeesByFirstname(firstname);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
		List<EmployeeResponse> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody Employee employee) {
		EmployeeResponse newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee employee) {
		EmployeeResponse newEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/sort")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployeesSorting(@RequestParam("order") String order) {
		List<EmployeeResponse> employees = employeeService.getAllEmployeesSorting(order);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<LoginResponse> authenticateEmployee(@RequestBody LoginRequest loginRequest) {
		LoginResponse authResponse = employeeService.loginEmployee(loginRequest);
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Integer id) {
		String response = employeeService.deleteEmployee(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}

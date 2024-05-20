package com.grade.employee.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeNotFoundExceptionHandler(EmployeeNotFoundException ex) {
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> sQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex) {
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

}

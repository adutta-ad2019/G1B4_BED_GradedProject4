package com.grade.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grade.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<List<Employee>> findByFirstName(String firstName);
	Optional<Employee> findByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT * FROM tbl_employees ORDER BY first_name ASC", nativeQuery = true)
	Optional<List<Employee>> findAllByFirstnameAsc();
	
	@Query(value = "SELECT * FROM tbl_employees ORDER BY first_name DESC", nativeQuery = true)
	Optional<List<Employee>> findAllByFirstnameDesc();

}

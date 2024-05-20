package com.grade.employee.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name = "tbl_roles")
public class Roles {

	@Id
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<Employee> employees;
}

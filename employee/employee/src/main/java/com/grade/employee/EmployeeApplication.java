package com.grade.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.grade.employee.entity.Roles;
import com.grade.employee.repository.RolesRepository;

@EnableWebMvc
@SpringBootApplication
public class EmployeeApplication implements CommandLineRunner {
	
	@Autowired
	RolesRepository rolesRepo;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		try {
			Roles roleAdmin = new Roles();
			roleAdmin.setId(1);
			roleAdmin.setName("ADMIN");

			Roles roleNormal = new Roles();
			roleNormal.setId(2);
			roleNormal.setName("NORMAL");

			List<Roles> roles = List.of(roleAdmin, roleNormal);
			rolesRepo.saveAll(roles);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}

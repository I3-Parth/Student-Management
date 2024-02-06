package com.springboot.studentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student-Course Management System",
				version = "1.0.0",
				description = "This is a Spring Boot project for managing students using REST API, PostgreSQL, Swagger UI, and JPA concepts.",
				termsOfService = "demoCode",
				contact = @Contact(
						name = "Parth Shah",
						email = "parth.shah@techprescient.com"
				),
				license = @License(
						name = "License",
						url = "demoCode"
				)
		)
)
public class StudentManagementApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}

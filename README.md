# Student Management System
This is a Spring Boot project for managing students using REST API, PostgreSQL, Swagger UI, and JPA concepts.

## Overview

- **Spring Boot Project**: This project is built using the Spring Boot framework, providing a robust and scalable architecture for managing students.

- **REST API**: The application exposes a set of RESTful APIs to perform CRUD operations on student entities and associated courses.

- **PostgreSQL Database**: The project utilizes a PostgreSQL database to store and manage student and course data.

- **Swagger UI**: Swagger UI is integrated, allowing you to explore and test the REST APIs easily. 

- **JPA Concepts**: The project leverages Java Persistence API (JPA) for seamless interaction with the database, simplifying the data access layer.

## Tables

The project involves two tables:

1. **Student Table**: Stores information about students.

2. **Course Table**: Stores information about courses.

## 1. Student Table
### Attributes
	- Id
	- Name
	- Academic Year
	- Department
	- Courses
 
### Functions
	- Fetch all Students
 	- Fetch Student by Id
	- Fetch Student by Name
 	- Create a new Student
	- Create multiple new Students
	- Update Student data
 	- Assign a Student to course
	- Delete Student by Id
 	- Delete all Students


## 2. Course Table
### Attributes
	- Id
 	- Title
	- Course Code
 	- Credits
	- Fees
 	- Students

### Functions
	- Fetch all Courses
 	- Fetch Course by Id
	- Featch Students by Course Id
	- Search Course by fees
 	- Create a new Course
	- Create multiple new Courses
 	- Update Course data
	- Delete Course by Id
 	- Delete all Courses
	- Delete Student from a Course

package com.springboot.studentManagement.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String dept;
    private String year;
}
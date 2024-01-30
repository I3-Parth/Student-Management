package com.springboot.studentManagement.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String dept;
    private String year;
}
package com.springboot.studentManagement.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateStudentDTO {
    private String fullName;
    private String Year;
    private String dept;
    private Set<UpdateCourseDTO> courses;
}

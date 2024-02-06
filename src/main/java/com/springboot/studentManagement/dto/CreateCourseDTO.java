package com.springboot.studentManagement.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateCourseDTO {
    private String title;
    private double courseCode;
    private int credits;
    private double fees;
    private Set<UpdateStudentDTO> students;
}

package com.springboot.studentManagement.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private double courseCode;
    private int credits;
}

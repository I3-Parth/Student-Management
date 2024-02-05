package com.springboot.studentManagement.dto;

import lombok.Data;

@Data
public class UpdateCourseDTO {
    private String title;
    private double courseCode;
    private int credits;
//    private double fees;
}

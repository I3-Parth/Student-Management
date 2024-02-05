package com.springboot.studentManagement.dto;

import lombok.Data;

@Data
public class UpdateStudentDTO {
    private String fullName;
    private String dept;
    private String year;
}

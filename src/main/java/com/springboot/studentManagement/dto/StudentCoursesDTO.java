package com.springboot.studentManagement.dto;

import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class StudentCoursesDTO {
    private Long id;
    private String name;
    private List<String> courses;
}
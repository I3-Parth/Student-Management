package com.springboot.studentManagement.dto;

import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class CourseStudentsDTO {
    private Long id;
    private String title;
    private List<String> students;
}
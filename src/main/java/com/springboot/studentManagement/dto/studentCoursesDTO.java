package com.springboot.studentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class studentCoursesDTO {
    private Long id;
    private String name;
    private List<String> courses;
}

package com.springboot.studentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class studentCoursesDTO {
    private Long id;
    private String name;
//    private String dept;
//    private String year;
    private List<String> courses;
}

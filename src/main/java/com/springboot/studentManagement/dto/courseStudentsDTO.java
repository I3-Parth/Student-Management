package com.springboot.studentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class courseStudentsDTO {
    private Long id;
    private String title;
    private List<String> students;
}

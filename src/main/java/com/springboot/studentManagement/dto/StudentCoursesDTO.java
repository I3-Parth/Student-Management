package com.springboot.studentManagement.dto;
import com.springboot.studentManagement.dto.CourseDTO;
import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class StudentCoursesDTO {
    private Long id;
    private String name;
    private List<CourseDTO> courses;
}
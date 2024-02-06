package com.springboot.studentManagement.dto;
import com.springboot.studentManagement.dto.StudentDTO;
import lombok.Data;

import java.util.List;

@Data
//@AllArgsConstructor
public class CourseStudentsDTO {
    private Long id;
    private String title;
    private List<StudentDTO> students;
}
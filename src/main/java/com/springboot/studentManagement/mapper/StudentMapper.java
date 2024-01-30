package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.StudentCoursesDTO;
import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.model.student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO modelToDTO(student student);
    student dtoToModel(StudentDTO studentDTO);

    @Mapping(source = "student.courses",target = "courses")
    StudentCoursesDTO getCoursesByStudent(student student);
}

package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.model.course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO modelToDTO(course course);
    course dtoToModel(CourseDTO courseDTO);

    @Mapping(source = "course.students", target = "students")
    CourseStudentsDTO getStudentsByCourse(course course);
}

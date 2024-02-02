package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.model.course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE= Mappers.getMapper(CourseMapper.class);
    CourseDTO modelToDTO(course course);
    course dtoToModel(CourseDTO courseDTO);
    @Mapping(source = "course.students", target = "students")
    @Mapping(target = "StudentDTO.fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    CourseStudentsDTO getStudentsByCourse(course course);

    default String getFullName(String firstName, String lastName){
        return firstName+" "+lastName;
    }
}

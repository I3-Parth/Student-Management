package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.StudentCoursesDTO;
import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.model.student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    StudentDTO modelToDTO(student student);
    student dtoToModel(StudentDTO studentDTO);


    @Mapping(source = "courses",target = "courses")
    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    @Mapping(target = "totalCourses", expression = "java(getTotalCoursesCount(student.getCourses()))")
    StudentCoursesDTO getCoursesByStudent(student student);
    default String getFullName(String firstName, String lastName){
        return firstName+" "+lastName;
    }
    default int getTotalCoursesCount(Set<course> courses){
        return courses != null ? courses.size() : 0;
    }
}

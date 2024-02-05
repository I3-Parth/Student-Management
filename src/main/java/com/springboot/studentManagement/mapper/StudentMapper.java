package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.StudentCoursesDTO;
import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.dto.UpdateStudentDTO;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.model.student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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

    @Mapping(target = "firstName", expression = "java(getFirstNameOfStudent(updateStudentDTO,student))")
    @Mapping(target = "lastName", expression = "java(getLastNameOfStudent(updateStudentDTO, student))")
    void updateStudent(UpdateStudentDTO updateStudentDTO, @MappingTarget student student);

    default String getFirstNameOfStudent(UpdateStudentDTO updateStudentDTO, student student){
        String fullname=updateStudentDTO.getFullName();
        if(fullname != null){
            String[] parts = fullname.split(" ");
            return parts[0];
        }
       return  student.getFirstName()+" "+student.getLastName();
    }
    default String getLastNameOfStudent(UpdateStudentDTO updateStudentDTO, student student){
        String fullname=updateStudentDTO.getFullName();
        if(fullname != null){
            String[] parts = fullname.split(" ");
            return parts[1];
        }
        return student.getFirstName()+" "+student.getLastName();
    }
}

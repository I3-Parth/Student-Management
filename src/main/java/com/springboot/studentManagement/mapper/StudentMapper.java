package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.*;
import com.springboot.studentManagement.model.Course;
import com.springboot.studentManagement.model.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    StudentDTO modelToDTO(Student student);
    Student dtoToModel(StudentDTO studentDTO);


    @Mapping(source = "courses",target = "courses")
    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    @Mapping(target = "totalCourses", expression = "java(getTotalCoursesCount(student.getCourses()))")
    StudentCoursesDTO getCoursesByStudent(Student student);

    @Mapping(target = "firstName", expression = "java(cFirstName(createStudentDTO))")
    @Mapping(target = "lastName", expression = "java(cLastName(createStudentDTO))")
    Student covertToEntity(CreateStudentDTO createStudentDTO);
    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    CreateStudentDTO convertEntityToStudent(Student student);


    Set<CreateStudentDTO> createModelToDTO(Set<Student> student);
    Set<Student> createDTOToModel(Set<CreateStudentDTO> createStudentDTO);

    default String cFirstName(CreateStudentDTO createStudentDTO){
        String fullname=createStudentDTO.getFullName();
        String[] parts = fullname.split(" ");
        return parts[0];
    }
    default String cLastName(CreateStudentDTO createStudentDTO){
        String fullname=createStudentDTO.getFullName();
        String[] parts = fullname.split(" ");
        return parts[1];
    }
    @Mapping(target = "firstName", expression = "java(getFirstNameOfStudent(updateStudentDTO,student))")
    @Mapping(target = "lastName", expression = "java(getLastNameOfStudent(updateStudentDTO, student))")
    void updateStudent(UpdateStudentDTO updateStudentDTO, @MappingTarget Student student);

    default String getFirstNameOfStudent(UpdateStudentDTO updateStudentDTO, Student student){
        String fullname=updateStudentDTO.getFullName();
        if(fullname != null){
            String[] parts = fullname.split(" ");
            return parts[0];
        }
       return  student.getFirstName()+" "+student.getLastName();
    }
    default String getLastNameOfStudent(UpdateStudentDTO updateStudentDTO, Student student){
        String fullname=updateStudentDTO.getFullName();
        if(fullname != null){
            String[] parts = fullname.split(" ");
            return parts[1];
        }
        return student.getFirstName()+" "+student.getLastName();
    }

    default String getFullName(String firstName, String lastName){
        return firstName+" "+lastName;
    }
    default int getTotalCoursesCount(Set<Course> courses){
        return courses != null ? courses.size() : 0;
    }
}

package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.model.course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO modelToDTO(course course);
    course dtoToModel(CourseDTO courseDTO);
    CourseStudentsDTO getStudentsByCourse(course course);
}

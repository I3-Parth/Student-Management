package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.model.course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO modelToDTO(course course);
    course dtoToModel(CourseDTO courseDTO);
}

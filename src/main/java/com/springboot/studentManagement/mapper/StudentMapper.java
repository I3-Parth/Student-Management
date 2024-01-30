package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.model.student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO modelToDTO(student student);
    student dtoToModel(StudentDTO studentDTO);
}

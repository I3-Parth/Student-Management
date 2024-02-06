package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.dto.CreateCourseDTO;
import com.springboot.studentManagement.dto.UpdateCourseDTO;
import com.springboot.studentManagement.model.course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = StudentMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO modelToDTO(course course);
    course dtoToModel(CourseDTO courseDTO);
    CourseStudentsDTO getStudentsByCourse(course course);

    course convertDTOToEntity(CreateCourseDTO createCourseDTO);
    CreateCourseDTO convertEntityToDTO(course course);

    Set<course> createDTOToEntity(Set<CreateCourseDTO> createCourseDTO);
    Set<CreateCourseDTO> createEntityToDTO(Set<course> course);

    void updateExisting(UpdateCourseDTO updateCourseDTO, @MappingTarget course course);
}

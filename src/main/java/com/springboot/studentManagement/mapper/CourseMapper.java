package com.springboot.studentManagement.mapper;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.dto.CreateCourseDTO;
import com.springboot.studentManagement.dto.UpdateCourseDTO;
import com.springboot.studentManagement.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = StudentMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO modelToDTO(Course course);
    Course dtoToModel(CourseDTO courseDTO);
    CourseStudentsDTO getStudentsByCourse(Course course);

    Course convertDTOToEntity(CreateCourseDTO createCourseDTO);
    CreateCourseDTO convertEntityToDTO(Course course);

    Set<Course> createDTOToEntity(Set<CreateCourseDTO> createCourseDTO);
    Set<CreateCourseDTO> createEntityToDTO(Set<Course> course);

    void updateExisting(UpdateCourseDTO updateCourseDTO, @MappingTarget Course course);
}

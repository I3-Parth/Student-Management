package com.springboot.studentManagement.services;

import com.springboot.studentManagement.dto.*;
import com.springboot.studentManagement.mapper.CourseMapper;
import com.springboot.studentManagement.model.Student;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.studentManagement.model.Course;
import org.springframework.stereotype.Service;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;
import com.springboot.studentManagement.repository.CourseRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseRepository courseRepository;
    public List<CourseDTO> getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::modelToDTO).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id)throws ResourceNotFoundException{
        return courseMapper.modelToDTO(courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id)));
    }

    public CourseStudentsDTO getStudentsByCourse(Long id)throws ResourceNotFoundException{
        return courseMapper.getStudentsByCourse(courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id)));
    }

    public Set<CreateCourseDTO> createCourseDTOS(Set<CreateCourseDTO> createCourseDTOS){
        Set<Course> courses=courseMapper.createDTOToEntity(createCourseDTOS);
        courseRepository.saveAll(courses);
        return courseMapper.createEntityToDTO(courses);
    }

    public CourseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO)throws ResourceNotFoundException{
        Course course = courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id));
        courseMapper.updateExisting(updateCourseDTO,course);
        Course course1 = courseRepository.save(course);
        return courseMapper.modelToDTO(course1);
    }
}

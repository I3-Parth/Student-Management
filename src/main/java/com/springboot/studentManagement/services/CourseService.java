package com.springboot.studentManagement.services;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.mapper.CourseMapper;
import com.springboot.studentManagement.model.student;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.studentManagement.model.course;
import org.springframework.stereotype.Service;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.dto.UpdateCourseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    courseRepository courseRepository;
    public List<CourseDTO> getAllCourses(){
        List<course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::modelToDTO).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id)throws resourceNotFoundException{
        return courseMapper.modelToDTO(courseRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public CourseStudentsDTO getStudentsByCourse(Long id)throws resourceNotFoundException{
        return courseMapper.getStudentsByCourse(courseRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public CourseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO)throws resourceNotFoundException{
        course course = courseRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        courseMapper.updateExisting(updateCourseDTO,course);
        course course1 = courseRepository.save(course);
        return courseMapper.modelToDTO(course1);
    }
}

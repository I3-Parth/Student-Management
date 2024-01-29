package com.springboot.studentManagement.service;

import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import com.springboot.studentManagement.model.student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.dto.courseDTO;
import com.springboot.studentManagement.dto.courseStudentsDTO;
import com.springboot.studentManagement.model.course;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class courseService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    courseRepository courseRepository;

    public List<courseDTO> getAllCourses(){
        return courseRepository.findAll().stream().map(this::converEntityToDTO).collect(Collectors.toList());
    }
    public courseDTO getCourseById(Long id)throws resourceNotFoundException{
        course course=courseRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        return converEntityToDTO(course);
    }
    private courseDTO converEntityToDTO(course course){
        courseDTO courseDTO = modelMapper.map(course, courseDTO.class);
        return courseDTO;
    }
    public courseStudentsDTO getCourseStudents(Long id)throws resourceNotFoundException{
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        course course=courseRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        List<String> students=course.getStudents().stream().map(student::getName).collect(Collectors.toList());
        courseStudentsDTO courseStudentsDTO=new courseStudentsDTO(course.getId(),course.getTitle(),students);
        return courseStudentsDTO;
    }
}

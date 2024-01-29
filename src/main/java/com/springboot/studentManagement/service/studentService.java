package com.springboot.studentManagement.service;

import com.springboot.studentManagement.dto.studentCoursesDTO;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import com.springboot.studentManagement.model.course;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.dto.studentDTO;
import com.springboot.studentManagement.model.student;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class studentService {
    @Autowired
    private studentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<studentDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public studentDTO getStudentById(Long id)throws resourceNotFoundException{
        student student = studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        return convertEntityToDto(student);
    }
    private studentDTO convertEntityToDto(student student){
        studentDTO studentDTO = modelMapper.map(student,com.springboot.studentManagement.dto.studentDTO.class);
        return studentDTO;
    }

    public studentCoursesDTO getStudentCourses(Long id)throws resourceNotFoundException{
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        student student = studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        List<String> courses=student.getCourses().stream().map(course::getTitle).collect(Collectors.toList());
        studentCoursesDTO studentCoursesDTO=new studentCoursesDTO(student.getId(),student.getName(),courses);
        return studentCoursesDTO;
    }
}

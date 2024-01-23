package com.springboot.studentManagement.service;

import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import org.modelmapper.ModelMapper;
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
        student student=studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id));
        return convertEntityToDto(student);
    }
    private studentDTO convertEntityToDto(student student){
        studentDTO studentDTO = modelMapper.map(student,com.springboot.studentManagement.dto.studentDTO.class);
        return studentDTO;
    }

}

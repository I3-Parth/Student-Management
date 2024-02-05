package com.springboot.studentManagement.services;

import com.springboot.studentManagement.dto.StudentCoursesDTO;
import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.dto.UpdateStudentDTO;
import com.springboot.studentManagement.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    studentRepository studentRepository;

    public List<StudentDTO> getAllStudents(){
        List<student> students=studentRepository.findAll();
        return students.stream().map(studentMapper::modelToDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id)throws resourceNotFoundException{
        return studentMapper.modelToDTO(studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public StudentCoursesDTO getCoursesByStudents(Long id)throws resourceNotFoundException{
        return studentMapper.getCoursesByStudent(studentRepository.findById(id).orElseThrow(()->new resourceNotFoundException(id)));
    }

    public StudentDTO updateStudent(Long id, UpdateStudentDTO updateStudentDTO)throws resourceNotFoundException{
        student student = studentRepository.findById(id).orElseThrow(()-> new resourceNotFoundException(id));
        studentMapper.updateStudent(updateStudentDTO, student);
        student student1 = studentRepository.save(student);
        return studentMapper.modelToDTO(student1);
    }
}

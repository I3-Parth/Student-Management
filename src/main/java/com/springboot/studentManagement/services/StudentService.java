package com.springboot.studentManagement.services;

import com.springboot.studentManagement.dto.*;
import com.springboot.studentManagement.mapper.StudentMapper;
import com.springboot.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.studentManagement.model.Student;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents(){
        List<Student> students=studentRepository.findAll();
        return students.stream().map(studentMapper::modelToDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id)throws ResourceNotFoundException{
        return studentMapper.modelToDTO(studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id)));
    }

    public StudentCoursesDTO getCoursesByStudents(Long id)throws ResourceNotFoundException{
        return studentMapper.getCoursesByStudent(studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id)));
    }

    public Set<CreateStudentDTO> createStudentDTOS(Set<CreateStudentDTO> createStudentDTOS){
        Set<Student> students=studentMapper.createDTOToModel(createStudentDTOS);
        this.studentRepository.saveAll(students);
        return studentMapper.createModelToDTO(students);
    }


    public StudentDTO updateStudent(Long id, UpdateStudentDTO updateStudentDTO)throws ResourceNotFoundException{
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        studentMapper.updateStudent(updateStudentDTO, student);
        Student student1 = studentRepository.save(student);
        return studentMapper.modelToDTO(student1);
    }
}

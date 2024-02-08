package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.dto.*;
import com.springboot.studentManagement.exceptions.InvalidAddressException;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class studentController {
    @Autowired
    studentRepository studentRepository;

    @Autowired
    courseRepository courseRepository;

    @Autowired
    StudentService studentService;
    // Get All Students
    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    // Get Student by ID
    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable(value = "id")Long studid) throws resourceNotFoundException {
        return studentService.getStudentById(studid);
    }

    // Get by Name
//    @GetMapping("/find/{name}")
//    public List<student> findbyname(@PathVariable String name){
//        return studentRepository.findByNameContaining(name);
//    }

    // Create Student
//    @PostMapping
//    public student createStudent(@RequestBody student student){
//        return this.studentRepository.save(student);
//    }

    // Create Multiple Students
    @PostMapping
    public Set<CreateStudentDTO> createMultipleStudents(@RequestBody Set<CreateStudentDTO> createStudentDTOS){
        return studentService.createStudentDTOS(createStudentDTOS);
    }

    @PostMapping("/{id}")
    public ShowStudentAddressesDTO alterWholeStudent(@PathVariable(value = "id")Long studentId,@Valid @RequestBody UpdateStudentDTO updateStudentDTO)throws resourceNotFoundException, InvalidAddressException{
        return studentService.updateWholeStudent(studentId,updateStudentDTO);
    }

    // Assign Student to a course
//    @PutMapping("/{sid}/course/{cid}")
//    public ResponseEntity<student> assignStudentToCourse(@PathVariable(value = "sid")Long sid, @PathVariable(value = "cid")Long cid)throws resourceNotFoundException{
//        course course=courseRepository.findById(cid).orElseThrow(()-> new resourceNotFoundException(cid));
//        student student=studentRepository.findById(sid).orElseThrow(()-> new resourceNotFoundException(sid));
//        student.getCourses().add(course);
//        course.getStudents().add(student);
//        courseRepository.save(course);
//        return ResponseEntity.ok(this.studentRepository.save(student));
//    }


//     Update Student
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable(value = "id")Long studid, @Validated @RequestBody UpdateStudentDTO studentDetails)throws resourceNotFoundException {
        return studentService.updateStudent(studid, studentDetails);
    }


    //Add-Update Student Address
//    @PutMapping("/{id}")
//    public ShowStudentAddressesDTO updateStudentWithAddresses(@PathVariable(value = "id")Long studentId, @Validated @RequestBody List<CreateStudentAddressesDTO> createStudentAddressesDTOS)throws resourceNotFoundException, InvalidAddressException {
//        return studentService.updateStudentAddresses(studentId,createStudentAddressesDTOS);
//    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studid)throws resourceNotFoundException {
        student student=studentRepository.findById(studid).orElseThrow(()->new resourceNotFoundException(studid));
        this.studentRepository.delete(student);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Student with id "+studid+" is deleted successfully",Boolean.TRUE);
        return response;
    }

    // Delete all students
    @DeleteMapping
    public Map<String, Boolean> deleteAllStudents(){
        this.studentRepository.deleteAll();
        Map<String, Boolean> response=new HashMap<>();
        response.put("All Students records deleted",Boolean.TRUE);
        return response;
    }


}

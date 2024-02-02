package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.dto.StudentDTO;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.services.StudentService;
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
    @PostMapping
    public student createStudent(@RequestBody student student){
        return this.studentRepository.save(student);
    }

    // Create Multiple Students
    @PostMapping("/createMultiple")
    public List<student> createMultipleStudents(@RequestBody List<student> students){
        return  this.studentRepository.saveAll(students);
    }

    // Assign Student to a course
    @PutMapping("/{sid}/course/{cid}")
    public ResponseEntity<student> assignStudentToCourse(@PathVariable(value = "sid")Long sid, @PathVariable(value = "cid")Long cid)throws resourceNotFoundException{
        course course=courseRepository.findById(cid).orElseThrow(()-> new resourceNotFoundException(cid));
        student student=studentRepository.findById(sid).orElseThrow(()-> new resourceNotFoundException(sid));
        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        return ResponseEntity.ok(this.studentRepository.save(student));
    }


    // Update Student
    @PutMapping("/{id}")
    public student updateStudent(@PathVariable(value = "id")Long studid, @Validated @RequestBody StudentDTO studentDetails)throws resourceNotFoundException {
        studentDetails.setId(studid);
        return studentService.updateStudent(studentDetails);
    }

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
    @DeleteMapping("/deleteAll")
    public Map<String, Boolean> deleteAllStudents(){
        this.studentRepository.deleteAll();
        Map<String, Boolean> response=new HashMap<>();
        response.put("All Students records deleted",Boolean.TRUE);
        return response;
    }


}

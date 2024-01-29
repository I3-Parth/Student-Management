package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.model.Course;
import com.springboot.studentManagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.studentManagement.repository.StudentRepository;
import com.springboot.studentManagement.repository.CourseRepository;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    // Get Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable(value = "id")Long studid) throws ResourceNotFoundException {
        Student student=studentRepository.findById(studid).orElseThrow(()->new ResourceNotFoundException(studid));
        return ResponseEntity.ok().body(student);
    }

    // Get by Name
    @GetMapping("/find/{name}")
    public List<Student> findbyname(@PathVariable String name){
        return studentRepository.findByNameContaining(name);
    }

    // Create Student
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return this.studentRepository.save(student);
    }

    // Create Multiple Students
    @PostMapping("/createMultiple")
    public List<Student> createMultipleStudents(@RequestBody List<Student> Students){
        return  this.studentRepository.saveAll(Students);
    }

    // Assign Student to a course
    @PutMapping("/{sid}/course/{cid}")
    public ResponseEntity<Student> assignStudentToCourse(@PathVariable(value = "sid")Long sid, @PathVariable(value = "cid")Long cid)throws ResourceNotFoundException {
        Course course=courseRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException(cid));
        Student student=studentRepository.findById(sid).orElseThrow(()-> new ResourceNotFoundException(sid));
        student.getCours().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        return ResponseEntity.ok(this.studentRepository.save(student));
    }


    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id")Long studid, @Validated @RequestBody Student studentDetails)throws ResourceNotFoundException {
        Student student=studentRepository.findById(studid).orElseThrow(()->new ResourceNotFoundException(studid));
        student.setCours(studentDetails.getCours());
        student.setDept(studentDetails.getDept());
        student.setName(studentDetails.getName());
        student.setYear(studentDetails.getYear());
        return ResponseEntity.ok(this.studentRepository.save(student));
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studid)throws ResourceNotFoundException {
        Student student=studentRepository.findById(studid).orElseThrow(()->new ResourceNotFoundException(studid));
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

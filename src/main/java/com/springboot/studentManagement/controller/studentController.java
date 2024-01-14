package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.model.course;
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

    // Get All Students
    @GetMapping
    public List<student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    // Get Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<student> getById(@PathVariable(value = "id")Long studid) throws resourceNotFoundException {
        student student=studentRepository.findById(studid).orElseThrow(()->new resourceNotFoundException(studid));
        return ResponseEntity.ok().body(student);
    }

    // Get by Name
    @GetMapping("/find/{name}")
    public List<student> findbyname(@PathVariable String name){
        return studentRepository.findByNameContaining(name);
    }

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

    //Assign Student to a course
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
    public ResponseEntity<student> updateStudent(@PathVariable(value = "id")Long studid, @Validated @RequestBody student studentDetails)throws resourceNotFoundException {
        student student=studentRepository.findById(studid).orElseThrow(()->new resourceNotFoundException(studid));
        student.setCourses(studentDetails.getCourses());
        student.setDept(studentDetails.getDept());
        student.setName(studentDetails.getName());
        student.setYear(studentDetails.getYear());
        return ResponseEntity.ok(this.studentRepository.save(student));
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

    //Delete by ID
    @DeleteMapping("/deleteAll")
    public Map<String, Boolean> deleteAllStudents(){
        this.studentRepository.deleteAll();
        Map<String, Boolean> response=new HashMap<>();
        response.put("All Students records deleted",Boolean.TRUE);
        return response;
    }


}

package com.springboot.studentManagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.studentManagement.repository.StudentRepository;
import com.springboot.studentManagement.repository.CourseRepository;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {
    StudentRepository studentRepository;
    CourseRepository courseRepository;


}

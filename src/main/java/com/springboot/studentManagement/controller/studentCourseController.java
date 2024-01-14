package com.springboot.studentManagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;

@RestController
@RequestMapping("/student/course")
public class studentCourseController {
    studentRepository studentRepository;
    courseRepository courseRepository;


}

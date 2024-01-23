package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.dto.studentCoursesDTO;
import com.springboot.studentManagement.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.service.courseService;
import com.springboot.studentManagement.service.studentService;
import com.springboot.studentManagement.model.student;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;

@RestController
@RequestMapping("")
public class studentCourseController {
    @Autowired
    courseRepository courseRepository;

    @Autowired
    studentRepository studentRepository;

    @Autowired
    courseService courseService;

    @Autowired
    studentService studentService;

    @GetMapping("students/{id}/courses")
    public studentCoursesDTO getStudentCourses(@PathVariable(value = "id")Long studId)throws resourceNotFoundException{
        return studentService.getStudentCourses(studId);
    }


}

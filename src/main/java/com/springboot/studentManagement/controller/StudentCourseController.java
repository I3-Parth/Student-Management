package com.springboot.studentManagement.controller;


import com.springboot.studentManagement.dto.CourseStudentsDTO;
import com.springboot.studentManagement.dto.StudentCoursesDTO;
import com.springboot.studentManagement.services.CourseService;
import com.springboot.studentManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;
import com.springboot.studentManagement.repository.StudentRepository;
import com.springboot.studentManagement.repository.CourseRepository;

@RestController
@RequestMapping("")
public class StudentCourseController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/students/{studentId}/courses")
    public StudentCoursesDTO getStudentCourses(@PathVariable(value = "studentId")Long studId)throws ResourceNotFoundException{
        return studentService.getCoursesByStudents(studId);
    }

    @GetMapping("/courses/{courseId}/students")
    public CourseStudentsDTO getCourseStudents(@PathVariable(value = "courseId")Long courID)throws ResourceNotFoundException{
        return courseService.getStudentsByCourse(courID);
    }
}

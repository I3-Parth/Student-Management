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
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.service.courseService;
import com.springboot.studentManagement.service.studentService;
import com.springboot.studentManagement.dto.courseStudentsDTO;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;

@RestController
@RequestMapping("")
public class studentCourseController {
    @Autowired
    studentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @GetMapping("/students/{studentId}/courses")
    public StudentCoursesDTO getStudentCourses(@PathVariable(value = "studentId")Long studId)throws resourceNotFoundException{
        return studentService.getCoursesByStudents(studId);
    }

    @GetMapping("/courses/{courseId}/students")
    public CourseStudentsDTO getCourseStudents(@PathVariable(value = "courseId")Long courID)throws resourceNotFoundException{
        return courseService.getStudentsByCourse(courID);
    }
}

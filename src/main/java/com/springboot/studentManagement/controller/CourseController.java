package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.dto.CourseDTO;
import com.springboot.studentManagement.dto.CreateCourseDTO;
import com.springboot.studentManagement.model.Student;
import com.springboot.studentManagement.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;
import com.springboot.studentManagement.dto.UpdateCourseDTO;
import com.springboot.studentManagement.model.Course;
import com.springboot.studentManagement.repository.StudentRepository;
import com.springboot.studentManagement.repository.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseService courseService;

    // Get All Courses
    @GetMapping
    public List<CourseDTO> getAllSCourses(){
        return courseService.getAllCourses();
    }

    // Get Courses by ID
    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable(value = "id")Long courid) throws ResourceNotFoundException {
        return courseService.getCourseById(courid);
    }

    // Get Courses by fees
//    @GetMapping("/find/{fees}")
//    public List<course> getCoursesByFees(@PathVariable double fees){
//        return courseRepository.findByFeesLessThan(fees);
//    }

    // Create Courses
//    @PostMapping
//    public course createCourses(@RequestBody course course){
//        return this.courseRepository.save(course);
//    }

    // Create Multiple Courses
    @PostMapping
    public Set<CreateCourseDTO> createMultipleCourses(@Valid @RequestBody Set<CreateCourseDTO> createCourseDTOS){
        return courseService.createCourseDTOS(createCourseDTOS);
    }

    // Update Courses
    @PutMapping("/{id}")
    public CourseDTO updateCourses(@PathVariable(value = "id")Long courid, @Validated @RequestBody UpdateCourseDTO courseDetails)throws ResourceNotFoundException {
        return courseService.updateCourse(courid, courseDetails);
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCourses(@PathVariable(value = "id") Long courid)throws ResourceNotFoundException {
        Course course=courseRepository.findById(courid).orElseThrow(()->new ResourceNotFoundException(courid));
        this.courseRepository.delete(course);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Course with id "+courid+" is deleted successfully",Boolean.TRUE);
        return response;
    }

    //Delete all courses
    @DeleteMapping
    public Map<String, Boolean> deleteAllCourses(){
        this.courseRepository.deleteAll();
        Map<String, Boolean> response=new HashMap<>();
        response.put("All courses records deleted",Boolean.TRUE);
        return response;
    }

    //Delete student from a course
    @DeleteMapping("/{cid}/student/{sid}")
    public  Map<String, Boolean> deleteStudentFromCourse(@PathVariable(value = "cid")Long cid,@PathVariable(value = "sid")Long sid) throws ResourceNotFoundException{
        Course course=courseRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException(cid));
        Student student=studentRepository.findById(sid).orElseThrow(()-> new ResourceNotFoundException(sid));
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentRepository.save(student);
        courseRepository.save(course);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Student is removed from course",Boolean.TRUE);
        return response;
    }

    // Get all students by course id
//    @GetMapping("/{id}/students")
//    public ResponseEntity<Set<student>> getStudentsByCourseId(@PathVariable(value = "id")Long courid)throws resourceNotFoundException{
//        course course=courseRepository.findById(courid).orElseThrow(()->new resourceNotFoundException(courid));
//        Set<student> students=course.getStudents();
//        return ResponseEntity.ok(students);
//    }
}

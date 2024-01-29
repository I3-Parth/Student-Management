package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.model.Course;
import com.springboot.studentManagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.studentManagement.exceptions.ResourceNotFoundException;
import com.springboot.studentManagement.repository.CourseRepository;
import com.springboot.studentManagement.repository.StudentRepository;

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

    // Get All Courses
    @GetMapping
    public List<Course> getAllSCourses(){
        return this.courseRepository.findAll();
    }

    // Get Courses by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable(value = "id")Long courid) throws ResourceNotFoundException {
        Course course=courseRepository.findById(courid).orElseThrow(()->new ResourceNotFoundException(courid));
        return ResponseEntity.ok().body(course);
    }

    // Get Courses by fees
    @GetMapping("/find/{fees}")
    public List<Course> getCoursesByFees(@PathVariable double fees){
        return courseRepository.findByFeesLessThan(fees);
    }

    // Create Courses
    @PostMapping
    public Course createCourses(@RequestBody Course course){
        return this.courseRepository.save(course);
    }

    // Create Multiple Courses
    @PostMapping("/createMultiple")
    public List<Course> createMultipleCourses(@RequestBody List<Course> cours){
        return  this.courseRepository.saveAll(cours);
    }

    // Update Courses
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourses(@PathVariable(value = "id")Long courid, @Validated @RequestBody Course courseDetails)throws ResourceNotFoundException {
        Course course=courseRepository.findById(courid).orElseThrow(()->new ResourceNotFoundException(courid));
        course.setCourseCode(courseDetails.getCourseCode());
        course.setStudents(courseDetails.getStudents());
        course.setFees(courseDetails.getFees());
        course.setTitle(courseDetails.getTitle());
        course.setCredits(courseDetails.getCredits());
        return ResponseEntity.ok(this.courseRepository.save(course));
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
    @DeleteMapping("/deleteAll")
    public Map<String, Boolean> deleteAllCourses(){
        this.courseRepository.deleteAll();
        Map<String, Boolean> response=new HashMap<>();
        response.put("All courses records deleted",Boolean.TRUE);
        return response;
    }

    //Delete student from a course
    @DeleteMapping("/{cid}/student/{sid}")
    public  Map<String, Boolean> deleteStudentFromCourse(@PathVariable(value = "cid")Long cid,@PathVariable(value = "sid")Long sid) throws ResourceNotFoundException {
        Course course=courseRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException(cid));
        Student student=studentRepository.findById(sid).orElseThrow(()-> new ResourceNotFoundException(sid));
        student.getCours().remove(course);
        course.getStudents().remove(student);
        studentRepository.save(student);
        courseRepository.save(course);
        Map<String, Boolean> response=new HashMap<>();
        response.put("Student is removed from course",Boolean.TRUE);
        return response;
    }

    // Get all students by course id
    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getStudentsByCourseId(@PathVariable(value = "id")Long courid)throws ResourceNotFoundException {
        Course course=courseRepository.findById(courid).orElseThrow(()->new ResourceNotFoundException(courid));
        Set<Student> Students =course.getStudents();
        return ResponseEntity.ok(Students);
    }
}

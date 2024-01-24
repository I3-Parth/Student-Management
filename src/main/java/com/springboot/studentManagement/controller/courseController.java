package com.springboot.studentManagement.controller;

import com.springboot.studentManagement.model.student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.studentManagement.exceptions.resourceNotFoundException;
import com.springboot.studentManagement.repository.courseRepository;
import com.springboot.studentManagement.repository.studentRepository;
import com.springboot.studentManagement.model.course;
import com.springboot.studentManagement.dto.courseDTO;
import com.springboot.studentManagement.service.courseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class courseController {
    @Autowired
    courseRepository courseRepository;

    @Autowired
    studentRepository studentRepository;

    @Autowired
    courseService courseService;

    // Get All Courses
    //    @GetMapping
    //    public List<course> getAllSCourses(){
    //        return this.courseRepository.findAll();
    //    }

    @GetMapping
    public List<courseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }


    // Get Courses by ID
    //    @GetMapping("/{id}")
    //    public ResponseEntity<course> getById(@PathVariable(value = "id")Long courid) throws resourceNotFoundException {
    //        course course=courseRepository.findById(courid).orElseThrow(()->new resourceNotFoundException(courid));
    //        return ResponseEntity.ok().body(course);
    //    }

    @GetMapping("/{id}")
    public courseDTO getCourseById(@PathVariable(value = "id")Long cId)throws resourceNotFoundException{
        return courseService.getCourseById(cId);
    }

    // Get Courses by fees
    @GetMapping("/find/{fees}")
    public List<course> getCoursesByFees(@PathVariable double fees){
        return courseRepository.findByFeesLessThan(fees);
    }

    // Create Courses
    @PostMapping
    public course createCourses(@Valid  @RequestBody course course){
        return this.courseRepository.save(course);
    }

    // Create Multiple Courses
    @PostMapping("/createMultiple")
    public List<course> createMultipleCourses(@Valid @RequestBody List<course> courses){
        return  this.courseRepository.saveAll(courses);
    }

    // Update Courses
    @PutMapping("/{id}")
    public ResponseEntity<course> updateCourses(@PathVariable(value = "id")Long courid, @Validated @RequestBody course courseDetails)throws resourceNotFoundException {
        course course=courseRepository.findById(courid).orElseThrow(()->new resourceNotFoundException(courid));
        course.setCourseCode(courseDetails.getCourseCode());
        course.setStudents(courseDetails.getStudents());
        course.setFees(courseDetails.getFees());
        course.setTitle(courseDetails.getTitle());
        course.setCredits(courseDetails.getCredits());
        return ResponseEntity.ok(this.courseRepository.save(course));
    }

    // Delete by ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCourses(@PathVariable(value = "id") Long courid)throws resourceNotFoundException {
        course course=courseRepository.findById(courid).orElseThrow(()->new resourceNotFoundException(courid));
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
    @DeleteMapping("/{courseId}/student/{studentId}")
    public  Map<String, Boolean> deleteStudentFromCourse(@PathVariable(value = "courseId")Long cid,@PathVariable(value = "studentId")Long sid) throws resourceNotFoundException{
        course course=courseRepository.findById(cid).orElseThrow(()-> new resourceNotFoundException(cid));
        student student=studentRepository.findById(sid).orElseThrow(()-> new resourceNotFoundException(sid));
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

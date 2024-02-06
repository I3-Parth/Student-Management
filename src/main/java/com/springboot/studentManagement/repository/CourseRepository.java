package com.springboot.studentManagement.repository;

import com.springboot.studentManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
//    List<Course> findByFeesLessThan(double fees);
}

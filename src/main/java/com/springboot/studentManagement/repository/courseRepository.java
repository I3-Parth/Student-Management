package com.springboot.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.studentManagement.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseRepository extends JpaRepository<Course, Long> {
    List<Course> findByFeesLessThan(double fees);
}

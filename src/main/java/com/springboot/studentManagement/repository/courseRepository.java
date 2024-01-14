package com.springboot.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.studentManagement.model.course;

import java.util.List;

public interface courseRepository extends JpaRepository<course, Long> {
    List<course> findByFeesLessThan(double fees);
}

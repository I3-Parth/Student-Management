package com.springboot.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.studentManagement.model.student;

import java.util.List;

public interface studentRepository extends JpaRepository<student, Long> {
    List<student> findByNameContaining(String name);
}

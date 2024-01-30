package com.springboot.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.studentManagement.model.student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface studentRepository extends JpaRepository<student, Long> {
    List<student> findByNameContaining(String name);
}

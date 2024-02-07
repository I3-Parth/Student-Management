package com.springboot.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.studentManagement.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<Student, Long> {
//    List<student> findByNameContaining(String name);
}

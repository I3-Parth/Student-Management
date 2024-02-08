package com.springboot.studentManagement.repository;

import com.springboot.studentManagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface addressRepository extends JpaRepository<Address, Long> {
//    List<Address> findAllByStudentId(Long id);
}

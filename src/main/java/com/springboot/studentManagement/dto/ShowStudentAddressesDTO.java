package com.springboot.studentManagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShowStudentAddressesDTO {
    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String dept;
    private String year;
    private List<AddressesDTO> addresses;
}
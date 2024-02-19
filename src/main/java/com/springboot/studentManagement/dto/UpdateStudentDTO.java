package com.springboot.studentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDTO {
    private String fullName;
    private String dept;
    private String year;
    private List<AddressesDTO> addresses;
}

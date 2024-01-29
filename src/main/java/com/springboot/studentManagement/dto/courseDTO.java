package com.springboot.studentManagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class courseDTO {
    private Long id;
    private String title;
    private double courseCode;
    private int credits;
}

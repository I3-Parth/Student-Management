package com.springboot.studentManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressesDTO {
    private Long id;
    @NotBlank(message = "Locality is mandatory")
    private String Locality;
    @NotBlank(message = "Landmark is mandatory")
    private String Landmark;
    @NotBlank(message = "City is mandatory")
    private String City;
}

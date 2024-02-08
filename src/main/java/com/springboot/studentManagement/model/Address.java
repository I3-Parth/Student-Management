

package com.springboot.studentManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
    @SequenceGenerator(name = "addressSequence", sequenceName = "addressSequenceTable", allocationSize = 1)
    private Long id;
    @NotBlank(message = "Locality is mandatory")
    @Column(name = "Locality")
    private String Locality;
    @NotBlank(message = "Landmark is mandatory")
    @Column(name = "Landmark")
    private String Landmark;
    @NotBlank(message = "City is mandatory")
    @Column(name = "City")
    private String City;
    @NotNull
    @ManyToOne
//    @JoinColumn(name = "studentId")
    private student student;
}

package com.springboot.studentManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_tbl_seq", allocationSize = 1)
    private Long id;
    @NotBlank(message = "Title is mandatory")
    @Column(name = "Title")
    private String title;
    @NotBlank(message = "Course code is mandatory")
    @Column(name = "Course_Code")
    private double courseCode;
    @NotBlank(message = "Credits is mandatory")
    @Column(name = "Credits")
    private int credits;
    @Max(50000)
    @NotBlank(message = "Fees is mandatory")
    @Column(name = "Fees")
    private double fees;
//    @Column(name = "Students")
    @ManyToMany( fetch = FetchType.LAZY,  mappedBy = "courses")
    @JsonBackReference
    private Set<student> students;
}

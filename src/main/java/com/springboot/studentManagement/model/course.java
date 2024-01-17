package com.springboot.studentManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Course_Code")
    private double courseCode;
    @Column(name = "Credits")
    private int credits;
    @Column(name = "Fees")
    private double fees;
//    @Column(name = "Students")
    @ManyToMany( fetch = FetchType.LAZY,  mappedBy = "courses")
    @JsonBackReference
    private Set<student> students;
}

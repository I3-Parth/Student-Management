package com.springboot.studentManagement.model;

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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_tbl_seq", allocationSize = 1)
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
    // @JsonBackReference
    private Set<Student> Students;
}

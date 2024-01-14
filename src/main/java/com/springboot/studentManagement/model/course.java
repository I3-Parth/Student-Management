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
public class course {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "Course_Code", nullable = false)
    private double courseCode;
    @Column(name = "Credits", nullable = false)
    private int credits;
    @Column(name = "Fees", nullable = false)
    private double fees;
    @Column(name = "Students", nullable = false)
    @ManyToMany(targetEntity = student.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courses")
    private Set<student> students;
}

package com.springboot.studentManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class student {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="Name", nullable = false)
    private String name;
    @Column(name = "Academic_Year",nullable = false)
    private String Year;
    @Column(name="Department",nullable = false)
    private String dept;
    @Column(name = "Courses", nullable = false)
    @ManyToMany(targetEntity = course.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "students")
    @JoinTable(name = "Student_course_tale",
        joinColumns = {
                @JoinColumn(name = "studentId",referencedColumnName = "id")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "courseId",referencedColumnName = "id")
        })
    private Set<course> courses;

}

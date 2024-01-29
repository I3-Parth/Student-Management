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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_tbl_seq", allocationSize = 1)
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name = "Academic_Year")
    private String Year;
    @Column(name="Department")
    private String dept;
//    @Column(name = "Courses")
    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Student_course_tale",
        joinColumns = {
                @JoinColumn(name = "studentId",referencedColumnName = "id")
        },
        inverseJoinColumns = {
                @JoinColumn(name = "courseId",referencedColumnName = "id")
        })
//    @JsonManagedReference
    private Set<Course> cours;

}

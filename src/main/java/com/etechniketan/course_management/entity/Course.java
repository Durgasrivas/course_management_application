package com.etechniketan.course_management.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "course")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, name = "title")
    private String courseTitle;

    @Column(length = 255, nullable = false, name = "description")
    private String courseDescription;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    @ToString.Exclude // References the 'courses' field in Student
    private List<Student> students = new ArrayList<>();


}

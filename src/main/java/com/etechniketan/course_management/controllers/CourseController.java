package com.etechniketan.course_management.controllers;


import com.etechniketan.course_management.entity.Course;
import com.etechniketan.course_management.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);

    }


    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @GetMapping("/{title}")
    public Course getCourseByTitle(@PathVariable String title) {
        return courseService.getAllCoursesByTitle(title);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {

        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/popular")
    public List<Course> getPopularCourses(
            @RequestParam(defaultValue = "1") int minStudents) {//Query Param
        return courseService.getPopularCourses(minStudents);
    }

}
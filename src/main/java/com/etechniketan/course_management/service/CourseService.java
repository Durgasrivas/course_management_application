package com.etechniketan.course_management.service;

import com.etechniketan.course_management.entity.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);

    List<Course> getAllCourses();

    Course getAllCoursesByTitle(String courseTitle);

    Course getCourseById(Long id);

    List<Course> getPopularCourses(int minStudents);

}

package com.etechniketan.course_management.service;

import com.etechniketan.course_management.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Student registerStudent(Student student);

    List<Student> getAllStudents();

    void enrollStudentInCourse(Long studentId, Long courseId);

    List<Student> getStudentsByCourseTitle(String courseTitle);

    List<Student> getStudentsByCity(String city);

    Page<Student> getAllStudentsPaged(int pageNumber, int sizePerPage, String sortByParameter, String direction);

}

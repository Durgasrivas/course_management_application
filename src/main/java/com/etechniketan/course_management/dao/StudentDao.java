package com.etechniketan.course_management.dao;

import com.etechniketan.course_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {



    List<Student> findByCourses_CourseTitle(String courseTitle);

    List<Student> findStudentByCity(String city);

}

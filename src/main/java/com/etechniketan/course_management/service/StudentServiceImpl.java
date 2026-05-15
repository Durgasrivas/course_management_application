package com.etechniketan.course_management.service;


import com.etechniketan.course_management.dao.CourseDao;
import com.etechniketan.course_management.dao.StudentDao;
import com.etechniketan.course_management.entity.Course;
import com.etechniketan.course_management.entity.Student;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;


    @Override
    public Student registerStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentDao.findById(studentId).
                orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseDao.findById(courseId).
                orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);

    }

    @Override
    public List<Student> getStudentsByCourseTitle(String courseTitle) {
        return studentDao.findByCourses_CourseTitle(courseTitle);
    }

    @Override
    public List<Student> getStudentsByCity(String city) {
        return studentDao.findStudentByCity(city);
    }

    @Override
    public Page<Student> getAllStudentsPaged(int pageNumber, int sizePerPage, String sortByParameter, String direction) {
//        sortByParameter ? aesc : desc

//        1: To create a Direction object
        Direction directionTrend = null;
        if (direction.equalsIgnoreCase("desc")) {
            directionTrend = Direction.DESC;
        } else if (direction.equalsIgnoreCase("asc")) {
            directionTrend = Direction.ASC;
        } else {
            throw new RuntimeException("Invalid sort direction");
        }

//        2. Create a Sort object
        Sort sort = Sort.by(directionTrend, sortByParameter);//TODO Make it safe


//        3. Create a Pageable
        Pageable pageRequest = PageRequest.of(pageNumber, sizePerPage, sort);

        return studentDao.findAll(pageRequest);
    }
}

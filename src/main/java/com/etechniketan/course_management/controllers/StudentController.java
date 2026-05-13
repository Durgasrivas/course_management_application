package com.etechniketan.course_management.controllers;


import com.etechniketan.course_management.dao.StudentDao;
import com.etechniketan.course_management.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;


    public StudentController(StudentDao studentDao) {

        this.studentDao = studentDao;
    }

    @GetMapping("/find-by-city/{city}")//Path Var OR URL Param
    public List<Student> findStudentsByCity(@PathVariable String city) {
        return studentDao.findStudentByCity(city);
    }


}

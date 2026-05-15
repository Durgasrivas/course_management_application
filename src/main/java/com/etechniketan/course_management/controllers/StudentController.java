package com.etechniketan.course_management.controllers;


import com.etechniketan.course_management.entity.Student;
import com.etechniketan.course_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @GetMapping("/{city}")//Path Var OR URL Param
    public List<Student> findStudentsByCity(@PathVariable String city) {
        return studentService.getStudentsByCity(city);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
//        return ResponseEntity.ok(studentService.registerStudent(student));
        return new ResponseEntity<>(studentService.registerStudent(student), HttpStatus.CREATED);

    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PutMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Student enrolled successfully!");
    }


    @GetMapping("/paged")
    public Page<Student> getAllStudentsPaged(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2") int sizePerPage,
            @RequestParam(defaultValue = "id") String sortByParameter,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return studentService.getAllStudentsPaged(pageNumber, sizePerPage, sortByParameter,
                direction);
    }
}

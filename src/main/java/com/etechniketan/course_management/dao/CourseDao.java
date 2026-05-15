package com.etechniketan.course_management.dao;


import com.etechniketan.course_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {//<Entity, And its Primary Key Type>


    Course findByCourseTitle(String courseTitle);

    @Query("SELECT c FROM Course c WHERE size(c.students) >= :minStudents")
    //HQL/JPQL
    List<Course> findPopularCourses(@Param("minStudents") int minStudents);


}

package com.platform.repository;

import com.platform.exception.CourseException;
import com.platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

    public Course findBycourseName(String courseName) throws CourseException;
}

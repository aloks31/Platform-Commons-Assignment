package com.platform.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.model.Course;
import com.platform.model.Student;
import com.platform.model.StudentAddress;
import com.platform.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent( @RequestParam Integer student_code, @RequestParam String date_of_birth, @RequestParam String email, @RequestParam String mobile, @RequestParam String parentsName, @RequestBody List<StudentAddress> address) throws StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        Student updateStudent = studentService.updateStudent(student_code, date, email, mobile, parentsName, address);
        return new ResponseEntity<Student>(updateStudent, HttpStatus.ACCEPTED);

    }


    @GetMapping("/courses")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam Integer student_code, @RequestParam String date_of_birth) throws CourseException, StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        List<Course> courses =  studentService.searchCourses(student_code, date);
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);

    }


    @DeleteMapping("/course")
    public ResponseEntity<String> leaveCourse(@RequestParam Integer student_code, @RequestParam String date_of_birth, @RequestParam Integer courseId) throws CourseException, StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        String deleteCourse = studentService.leaveCourse(student_code, date, courseId);
        return new ResponseEntity<String>(deleteCourse, HttpStatus.OK);

    }


}
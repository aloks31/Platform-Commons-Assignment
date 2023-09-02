package com.platform.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;

import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.model.Course;
import com.platform.model.Student;
import com.platform.model.StudentAddress;
import com.platform.repository.CourseRepo;
import com.platform.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;


    @Override
    public Student updateStudent(Integer student_code, LocalDate date_of_birth, String email, 
    		String mobile, String parentsName, List<StudentAddress> address) throws StudentException {

  
        Student updateStudent = studentRepo.findBystudentCode(student_code);
        if (updateStudent == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }

        updateStudent = studentRepo.findByDateOfBirth(date_of_birth);
        if (updateStudent == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }

        updateStudent.setEmail(email);
        updateStudent.setMobile(mobile);
        updateStudent.setParent_name(parentsName);
        updateStudent.setStudentAddresses(address);

        return studentRepo.save(updateStudent);
    }


    @Override
    public List<Course> searchCourses(Integer student_code, LocalDate date_of_birth) throws CourseException, StudentException {


        Student student = studentRepo.findBystudentCode(student_code);
        if (student == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }

        student = studentRepo.findByDateOfBirth(date_of_birth);
        if (student == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }


        List<Course> searchCourse = student.getCourses();
        if (searchCourse.isEmpty()){
            throw new CourseException("No Courses Allocated to the Student yet.");
        }

        return searchCourse;
    }


    @Override
    public String leaveCourse(Integer student_code, LocalDate date_of_birth, Integer courseId) throws CourseException, StudentException {


        Student student = studentRepo.findBystudentCode(student_code);
        if (student == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }


        student = studentRepo.findByDateOfBirth(date_of_birth);
        if (student == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }


        List<Course> course = student.getCourses();
        if (course.isEmpty()){
            throw new CourseException("No Courses Allocated to the Student yet.");
        }

        Optional<Course> course1 = courseRepo.findById(courseId);

        courseRepo.deleteById(courseId);
        studentRepo.deleteAllByIdInBatch(Collections.singleton(course1.get().getCourseId()));

        student.getCourses().clear();

        return "Course Deleted Successfully : " ;

    }


}

package com.platform.service;

import com.platform.exception.CourseException;
import com.platform.exception.LoginException;
import com.platform.exception.StudentException;
import com.platform.model.Admin;
import com.platform.model.Course;
import com.platform.model.Student;

import java.util.List;

public interface AdminService {

    public Admin createAdminAccount(Admin admin) throws LoginException;

    public Student admitStudent(String key, Student student) throws StudentException, LoginException;

    public Course createCourse(String key, Course course) throws CourseException, LoginException;

    public Student getStudentByName(String key,String student_name) throws StudentException, 
    LoginException;

    public List<Student> getStudentsByCourse(String key,String courseName ) throws StudentException, 
    CourseException, LoginException;

    public String addStudentToTheCourse(String key, String courseName, Integer roll_number) 
    		throws StudentException, CourseException, LoginException;


}

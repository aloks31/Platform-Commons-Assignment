package com.platform.service;

import com.platform.exception.CourseException;
import com.platform.exception.LoginException;
import com.platform.exception.StudentException;
import com.platform.model.Admin;
import com.platform.model.Course;
import com.platform.model.CurrentUserSession;
import com.platform.model.Student;
import com.platform.repository.AdminRepo;
import com.platform.repository.CourseRepo;
import com.platform.repository.CurrentSessionRepo;
import com.platform.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CurrentSessionRepo currentSessionRepo;



    @Override
    public Admin createAdminAccount( Admin admin) throws LoginException {

        List<Admin> admins = adminRepo.findAdminByMobile(admin.getMobileNumber());

        if(admins.isEmpty()) {

            return adminRepo.save(admin);
        }
        throw new LoginException("Duplicate Mobile Number [ Already Registered with different customer ] ");

    }

     @Override
    public Student admitStudent(String key, Student student) throws StudentException, LoginException {

       
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        
        Student students = studentRepo.findBystudentCode(student.getStudentCode());
        if (students != null){
            throw new StudentException("Student Already exist with given Student Code ");
        }

        Student admitstudent = studentRepo.save(student);

        return admitstudent;
    }


     @Override
    public Course createCourse(String key, Course course) throws CourseException, LoginException {

       
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

     
        Course courses = courseRepo.findBycourseName(course.getCourseName());
        if (courses != null){
            new CourseException("Course With Given Name Already Exist");
        }

        Course createCourse = courseRepo.save(course);

        return createCourse;
    }


    @Override
    public Student getStudentByName(String key, String student_name) throws StudentException, LoginException {

 
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        
        Student student = studentRepo.findByStudentName(student_name);

        if (student == null){
            throw new StudentException("No student found with the Student Name : " + student_name);
        }
        return student;
    }


     @Override
    public List<Student> getStudentsByCourse(String key, String courseName) throws StudentException, CourseException, LoginException {

        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        Course course = courseRepo.findBycourseName(courseName);

        if (course == null){
            throw new CourseException("No Course Found with given courseName : " + courseName);
        }

        List<Student> students = course.getStudents();

        if (students.isEmpty()){
            throw new StudentException("No Student Allocated to the course");
        }

        return students;
    }


    @Override
    public String addStudentToTheCourse(String key, String courseName, Integer roll_number) throws StudentException, CourseException, LoginException {

        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        Course course = courseRepo.findBycourseName(courseName);
        Student student = studentRepo.findBystudentCode(roll_number);

        if (student != null) {
            if (!student.equals(course)) {
                student.getCourses().add(course);  // allocate Course
            }
            studentRepo.save(student);

            return "Student with Roll Number ' "+ roll_number + " ' and Name ' "+ student.getStudentName() + " ' allocated to ' " + course.getCourseName() + " ' Course Successfully." + "[Course Id : "+ course.getCourseId() + " ]";
        }

        return "Unable to Allocate Given Course To Student";
    }



}

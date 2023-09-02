package com.platform.service;

import java.time.LocalDate;
import java.util.List;
import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.model.Course;
import com.platform.model.Student;
import com.platform.model.StudentAddress;

public interface StudentService{
	
    public Student updateStudent(Integer student_code, LocalDate date_of_birth, String email, 
    		String mobile, String parentsName, List<StudentAddress> address) throws StudentException;

    public List<Course> searchCourses(Integer student_code, LocalDate date_of_birth) 
    		throws CourseException, StudentException;

    public String leaveCourse(Integer student_code, LocalDate date_of_birth, Integer courseId) 
    		throws CourseException, StudentException;


}

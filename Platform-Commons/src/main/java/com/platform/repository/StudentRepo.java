package com.platform.repository;

import com.platform.exception.StudentException;
import com.platform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    public  Student findBystudentCode(Integer student_code) throws StudentException;

    public Student findByStudentName(String studentName) throws StudentException;

    public Student findByDateOfBirth(LocalDate date_of_birth);

}

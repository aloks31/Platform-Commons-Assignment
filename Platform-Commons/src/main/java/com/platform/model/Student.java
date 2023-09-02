package com.platform.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
	    private Integer roll_number;

	    @NotNull
	    @Column(unique = true, name = "student_code")
	    private Integer studentCode;

	    @NotNull
	    @Size(min = 3, message = "Invalid Student name [ contains at least 3 characters ]")
	    private String studentName;

	    @NotNull
	    @Email(message = "Invalid Email Format [ abc@gmail.com ]")
	    private String email;

	    @NotNull
	    @Size(min = 10, max = 10 ,message = "Invalid Mobile Number [ 10 Digit Only ] ")
	    private String mobile;

	    @NotNull
	    @Size(min = 3, message = "Invalid Parent name [ contains at least 3 characters ]")
	    private String parent_name;

	    @NotNull
	    @Column(unique = true)
	    private LocalDate dateOfBirth;

	    @Enumerated
	    private Gender gender;

	    @JsonIgnore
	    @OneToMany(cascade = CascadeType.ALL)
	    private List<StudentAddress> StudentAddresses = new ArrayList<>();

	    @JsonIgnore
	    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "STUDENTS_COURSES", joinColumns = { @JoinColumn(name = "roll_number") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
	    private List<Course> courses;

	    public Student(Integer studentCode, String studentName, String email, String mobile, String parent_name, LocalDate dateOfBirth, Gender gender) {
	        this.studentCode = studentCode;
	        this.studentName = studentName;
	        this.email = email;
	        this.mobile = mobile;
	        this.parent_name = parent_name;
	        this.dateOfBirth = dateOfBirth;
	        this.gender = gender;
	    }

	    public Student(Integer studentCode, String studentName, String email, String mobile, String parent_name, LocalDate dateOfBirth, Gender gender, List<StudentAddress> StudentAddresses) {
	        this.studentCode = studentCode;
	        this.studentName = studentName;
	        this.email = email;
	        this.mobile = mobile;
	        this.parent_name = parent_name;
	        this.dateOfBirth = dateOfBirth;
	        this.gender = gender;
	        this.StudentAddresses = StudentAddresses;
	    }

}

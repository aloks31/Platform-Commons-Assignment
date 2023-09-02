package com.platform.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.model.enums.CourseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence")
    private Integer courseId;

    @NotNull
    @Size(min = 3, message = "Invalid  name [ contains at least 3 characters ]")
    private String courseName;

    @NotNull
    @Size(min = 5, message = "Invalid  Description [ contains at least 3 characters ]")
    private String description;

    @NotNull
    @Size(min = 1, message = "Invalid  Duration [ contains at least 3 characters ]")
    private String duration;

    @NotNull
    @Size(min = 3, message = "Invalid  Topics name [ contains at least 3 characters ]")
    private String topics;

    @Enumerated
    private CourseType courseType;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course(String courseName, String description, String duration, String topics) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.topics = topics;
    }

    public Course(String courseName, String description, String duration, String topics, CourseType courseType) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.topics = topics;
        this.courseType = courseType;
    }


}

package com.platform.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class StudentDTO {

    private Integer roll_number;
    private Integer studentCode;
    private String studentName;
    private String email;
    private String mobile;
    private String parent_name;
    private LocalDate date_of_birth;
    private Set<String> courses = new HashSet<>();

}

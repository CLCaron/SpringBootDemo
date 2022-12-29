package com.chriscaron.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(1L,
                        "Chris",
                        "chriscaron@fakeemail.com",
                        LocalDate.of(1993, 11, 15),
                        29)
        );
    }
}

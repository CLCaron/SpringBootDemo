package com.chriscaron.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student chris = new Student(
                    1L,
                    "Chris",
                    "chris@fakeemail.com",
                    LocalDate.of(1993, NOVEMBER, 15)
            );

            Student marissa = new Student(
                    "Marissa",
                    "marissa@fakeemail.com",
                    LocalDate.of(1995, JUNE, 30)
            );

            repository.saveAll(
                    List.of(chris, marissa)
            );
        };
    }
}

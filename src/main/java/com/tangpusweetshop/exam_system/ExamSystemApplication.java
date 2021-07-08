package com.tangpusweetshop.exam_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamSystemApplication {
    public static final String secret = "AJwtSecret";
    public static void main(String[] args) {
        SpringApplication.run(ExamSystemApplication.class, args);
    }

}

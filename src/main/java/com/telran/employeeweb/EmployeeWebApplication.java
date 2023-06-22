package com.telran.employeeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class EmployeeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeWebApplication.class, args);
    }

}

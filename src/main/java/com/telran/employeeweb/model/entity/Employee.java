package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

//    @Column(name = "name_in_database")
    private String name;

    private String surname;

    private int age;

    public Employee() {
    }

}

package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

//    @Column(name = "name_in_database")
    @NotBlank(message = "Put a valid name, please")
    @Length(max = 20, message = "Max length is 20")
    private String name;

    @NotBlank(message = "Put a valid surname, please")
    @Length(max = 20, message = "Max length is 20")
    private String surname;

    @Min(value = 18, message = "Age cannot be under 18")
    private Integer age;

    @Length(max = 255, message = "Max length is 255")
    private String additionalInfo;

    @Email(regexp = "^$|([a-z]+@[a-z]+.[a-z]+)", message = "Enter a valid email")
//    @Pattern(regexp = "[a-z]+@[a-z]+.[a-z]+")
    private String email;

    public Employee() {
    }

}

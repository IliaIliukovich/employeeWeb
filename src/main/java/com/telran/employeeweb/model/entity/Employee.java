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
    @NotBlank(message = "{validation.employee.name}")
    @Length(max = 20, message = "{validation.employee.name.length}")
    private String name;

    @NotBlank(message = "{validation.employee.surname}")
    @Length(max = 20, message = "{validation.employee.surname.length}")
    private String surname;

    @Min(value = 18, message = "{validation.employee.age}")
    private Integer age;

    @Length(max = 255, message = "{validation.employee.additionalinfo}")
    private String additionalInfo;

    @Email(regexp = "^$|([a-z]+@[a-z]+.[a-z]+)", message = "{validation.employee.email}")
//    @Pattern(regexp = "[a-z]+@[a-z]+.[a-z]+")
    private String email;

    @OneToOne
    @JoinColumn(name = "personaldetail_id")
    private PersonalDetail personalDetail;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", surname='" + surname + "}'";
    }
}

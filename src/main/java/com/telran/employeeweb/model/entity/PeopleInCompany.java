package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Entity
@Getter
@Setter
public abstract class PeopleInCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //    @Basic
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personaldetail_id")
    private PersonalDetail personalDetail;

    @Embedded
    private JobPosition jobPosition;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "id_employee"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private List<Project> projects = new ArrayList<>();


}

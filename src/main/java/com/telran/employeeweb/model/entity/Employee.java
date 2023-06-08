package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee  extends PeopleInCompany {

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @Transient
    private String temporalData;

    @Override
    public String toString() {
        return "Employee{" + "id='" + super.getId() + '\'' + ", name='" + super.getName()
                + '\'' + ", surname='" + super.getSurname() + "}'";
    }
}

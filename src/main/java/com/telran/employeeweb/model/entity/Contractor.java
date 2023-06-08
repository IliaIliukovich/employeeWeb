package com.telran.employeeweb.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contractor extends PeopleInCompany {

    private String contractInfo;

}

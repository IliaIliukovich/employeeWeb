package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PersonalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String passportData;

    private String homeAddress;

}

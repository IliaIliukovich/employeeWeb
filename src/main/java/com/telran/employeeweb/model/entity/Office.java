package com.telran.employeeweb.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String officeName;

    private String address;

}

package com.telran.employeeweb.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class JobPosition {

    private String jobName;

    private String responsibilities;

    @Enumerated(EnumType.STRING)
    private JobRole jobRole;

//    @Embedded
//    private JobPositionDetails;

    public enum JobRole {
        MANAGER, DEVELOPER, GENERAL_STAFF
    }


}

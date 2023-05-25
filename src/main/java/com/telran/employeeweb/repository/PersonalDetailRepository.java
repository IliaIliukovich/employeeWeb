package com.telran.employeeweb.repository;

import com.telran.employeeweb.model.entity.PersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailRepository extends JpaRepository<PersonalDetail, Long> {

}

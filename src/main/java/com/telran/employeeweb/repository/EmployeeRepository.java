package com.telran.employeeweb.repository;

import com.telran.employeeweb.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}

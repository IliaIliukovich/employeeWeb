package com.telran.employeeweb.service;

import com.telran.employeeweb.model.entity.Employee;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getEmployees();

    List<Employee> findEmployeeByNameOrSurname(String id, String name);

    Optional<Employee> findById(String id);

    Page<Employee> findAllByAgeGreaterThanEqual(Integer age, Pageable pageable);

    Employee addOrUpdate(Employee employee);

    Employee updateEmployeeSurnameAndAge(String id, String surname, int age);

    void deleteEmployee(String id);

}

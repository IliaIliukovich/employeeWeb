package com.telran.employeeweb.service;

import com.telran.employeeweb.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getEmployees();

    List<Employee> findEmployeeByNameAndSurname(String id, String name);

    Optional<Employee> findById(String id);

    void add(Employee employee);

    boolean updateEmployee(Employee employee);

    Employee updateEmployeeSurnameAndAge(String id, String surname, int age);

    void deleteEmployee(String id);

}

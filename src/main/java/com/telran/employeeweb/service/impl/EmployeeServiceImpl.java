package com.telran.employeeweb.service.impl;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.repository.EmployeeRepository;
import com.telran.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public List<Employee> findEmployeeByNameAndSurname(String name, String surname) {
        return repository.findAllByNameOrSurname(name, surname);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void add(Employee employee) {
        repository.save(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Optional<Employee> byId = repository.findById(employee.getId());
        if (byId.isPresent()){
            repository.save(employee);
            return true;
        }
        repository.save(employee);
        return false;
    }

    @Override
    public Employee updateEmployeeSurnameAndAge(String id, String surname, int age) {
        Optional<Employee> byId = repository.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            employee.setSurname(surname);
            employee.setAge(age);
            repository.save(employee);
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }
}

package com.telran.employeeweb;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.addAll(Arrays.asList(
                new Employee("Tom"),
                new Employee("Jane"),
                new Employee("Mary"),
                new Employee("Mark")
        ));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees(){
        return employees;
    }

//    @GetMapping(value = "/employees")
//    public List<Employee> getEmployees(){
//        return employees;
//    }

    @PostMapping(value = "/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping(value = "/employees")
    public Employee updateEmplyee(@RequestBody Employee employee){
        Employee employeeFromList =
                employees.stream().filter(e -> employee.getId().equals(e.getId())).findAny().get();
        employeeFromList.setName(employee.getName());
        return employee;
    }

    @DeleteMapping(value = "/employees/{id}")
    public List<Employee> deleteEmployee(@RequestParam String id){
        Employee employeeFromList =
                employees.stream().filter(e -> id.equals(e.getId())).findAny().get();
        employees.remove(employeeFromList);
        return employees;
    }
}

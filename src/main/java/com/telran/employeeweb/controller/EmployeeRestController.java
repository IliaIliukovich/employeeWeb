package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employeesRest")
public class EmployeeRestController {

    private final EmployeeService service;

    @Autowired
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return service.getEmployees();
    }

    @GetMapping(value = "/find")
    public List<Employee>findEmployee(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String surname){
        return service.findEmployeeByNameOrSurname(name, surname);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = service.addOrUpdate(employee);
        return updatedEmployee;
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = service.addOrUpdate(employee);
        boolean isUpdated = updatedEmployee.getId().equals(employee.getId());
        return new ResponseEntity<>(updatedEmployee, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeSurnameAndAge(@PathVariable String id,
                                                                @RequestParam String surname,
                                                                @RequestParam int age){
        Employee employee = service.updateEmployeeSurnameAndAge(id, surname, age);
        return new ResponseEntity<>(employee, employee != null ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable String id){
        System.out.println("Deleting:" + id);
        service.deleteEmployee(id);
    }
}

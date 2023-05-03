package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/page")
    public Page<Employee> getEmployeesPage(@PageableDefault(size = 5) Pageable pageable){
        return service.getEmployees(pageable);
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
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee){
        String employeeId = employee.getId();
        Employee updatedEmployee = service.addOrUpdate(employee);
        boolean isUpdated = updatedEmployee.getId().equals(employeeId);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

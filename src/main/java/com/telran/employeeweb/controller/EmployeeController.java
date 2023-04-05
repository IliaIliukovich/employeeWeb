package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model){
        List<Employee> employees = service.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

}

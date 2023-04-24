package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Value("${employeeNotPresentMessage}")
    private String employeeNotPresentMessage;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String getEmployees(Model model){
        List<Employee> employees = service.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               Model model){
        List<Employee> list = service.findEmployeeByNameAndSurname(name, surname);
        if (!list.isEmpty()) {
            model.addAttribute("foundEmployees", list);
        } else {
            model.addAttribute("notPresentMessage", employeeNotPresentMessage);
        }
        return "findPage";
    }

    @PostMapping
    public String addEmployee(@ModelAttribute Employee employeeToAdd, RedirectAttributes attributes) {
        service.add(employeeToAdd);
        attributes.addFlashAttribute("added", employeeToAdd.getId());
        return "redirect:/employees";
    }

    @PostMapping(value = "/edit")
    public String editEmployee(@RequestParam String employeeId, RedirectAttributes attributes){
        System.out.println("Editing " + employeeId);
        attributes.addFlashAttribute("editEmployeeId", employeeId);
        return "redirect:/employees/editEmployeePage";
    }

    @PostMapping(value = "/delete")
    public String deleteEmployee(@RequestParam String employeeId, RedirectAttributes attributes){
        System.out.println("Delete " + employeeId);
        service.deleteEmployee(employeeId);
        attributes.addFlashAttribute("deleted", employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/editEmployeePage")
    public String editEmployeePage(Model model, HttpServletRequest request){
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        String employeeId = (String)flashMap.get("editEmployeeId");
        Optional<Employee> employee = service.findById(employeeId);
        if (employee.isPresent()) {
            model.addAttribute("editEmployee", employee.get());
        }
        return "editEmployeePage";
    }

    @ModelAttribute("employeeToAdd")
    public Employee getEmployee(){
        return new Employee();
    }

}

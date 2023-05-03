package com.telran.employeeweb.controller;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
@SessionAttributes("editEmployee")
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
        model.addAttribute("employeeToAdd", new Employee());
        return "employees";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String surname,
                               Model model){
        List<Employee> list = service.findEmployeeByNameOrSurname(name, surname);
        if (!list.isEmpty()) {
            model.addAttribute("foundEmployees", list);
        } else {
            model.addAttribute("notPresentMessage", employeeNotPresentMessage);
        }
        return "findPage";
    }

    @GetMapping("/findByAge")
    public String findByAge(@RequestParam Integer age,
                            @PageableDefault(size = 5)
                            @SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.ASC)})
                            Pageable pageable, Model model){
        Sort sort = pageable.getSort();
        Page<Employee> page = service.findAllByAgeGreaterThanEqual(age, pageable);
        model.addAttribute("page", page);
        String sortBy = "";
        if (sort.stream().iterator().hasNext()) {
            Sort.Order sortOrder = sort.stream().iterator().next();
            String property = sortOrder.getProperty();
            String order = sortOrder.getDirection().name();
            sortBy = property + "," + order;
        }
        model.addAttribute("sortBy", sortBy);
        return "findByAgePage";
    }

    @PostMapping
    public String addEmployee(@Valid @ModelAttribute Employee employeeToAdd, RedirectAttributes attributes) {
        Employee added = service.addOrUpdate(employeeToAdd);
        attributes.addFlashAttribute("added", added.getId());
        return "redirect:/employees";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String processException(RedirectAttributes attributes) {
        attributes.addFlashAttribute("error", "Please check input data");
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
        if (flashMap != null) {
            String employeeId = (String) flashMap.get("editEmployeeId");
            if (employeeId != null) {
                Optional<Employee> employee = service.findById(employeeId);
                employee.ifPresent(value -> model.addAttribute("editEmployee", value));
            }
        }
        return "editEmployeePage";
    }

    @PostMapping("/editEmployeePage")
    public String sendEditedEmployee(@ModelAttribute Employee employee, Model model){
        model.addAttribute("editEmployee", employee);
        return "redirect:/employees/editEmployeePage2";
    }

    @GetMapping("/editEmployeePage2")
    public String editEmployeePage2(){
        return "editEmployeePage2";
    }

    @PostMapping("/editEmployeePage2")
    public String sendEditedEmployee2(@ModelAttribute Employee employee, Model model){
        model.addAttribute("editEmployee", employee);
        return "redirect:/employees/confirmPage";
    }

    @GetMapping("/confirmPage")
    public String confirmEmployeePage(){
        return "confirmEmployeeEditPage";
    }

    @PostMapping("/confirmPage")
    public String updateEmployee(Model model, RedirectAttributes attributes, SessionStatus status){
        Employee employee = (Employee) model.getAttribute("editEmployee");
        Employee updated = service.addOrUpdate(employee);
        attributes.addFlashAttribute("updated", updated.getId());
        status.setComplete();
        return "redirect:/employees";
    }

    @ModelAttribute("editEmployee")
    public Employee getEditedEmployee(){
        System.out.println("new Employee to edit added in Model");
        return new Employee();
    }

}

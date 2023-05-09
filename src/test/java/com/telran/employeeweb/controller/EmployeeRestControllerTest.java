package com.telran.employeeweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.service.EmployeeService;
import com.telran.employeeweb.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {EmployeeRestController.class, UserValidator.class})
public class EmployeeRestControllerTest {

    @MockBean
    private EmployeeService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employeesRest").contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Mary");

        mockMvc.perform(post("/employeesRest")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId("TestId");
        employee.setName("Mary");
        employee.setSurname("Petrova");

        Mockito.when(service.addOrUpdate(any())).thenReturn(employee);

        mockMvc.perform(put("/employeesRest")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());

        Employee employee2 = new Employee();
        employee2.setId("TestId");
        employee2.setName("Mary");
        employee2.setSurname("Petrova");
        employee2.setAge(10);

        mockMvc.perform(put("/employeesRest")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee2)))
                .andExpect(status().isBadRequest());
    }

}
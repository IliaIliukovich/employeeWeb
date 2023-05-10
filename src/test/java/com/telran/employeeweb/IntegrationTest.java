package com.telran.employeeweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    EmployeeRepository repository;


    @Test
    public void testRequest() throws Exception {
        Employee employee = new Employee();
        employee.setName("Mary");
        employee.setSurname("Poppins");

        mockMvc.perform(post("/employeesRest")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());

        List<Employee> employees = repository.findAllByNameOrSurname(null, "Poppins");
        assertEquals("Mary", employees.get(0).getName());
    }


}

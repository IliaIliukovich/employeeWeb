package com.telran.employeeweb.repository;

import com.telran.employeeweb.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;

    @Test
    void testDelete() {
        repository.deleteById("351df916-a056-468a-8d23-43cd4ef242b6");
        assertEquals(9, repository.findAll().size());
    }

    @Test
    void findAllByNameOrSurname() {
        List<Employee> employees = repository.findAllByNameOrSurname("Bob", null);
        assertEquals(2, employees.size());
    }

    @Test
    void testCount() {
        assertEquals(10, repository.count());
    }

}
package com.telran.employeeweb.service.impl;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.repository.EmployeeRepository;
import com.telran.employeeweb.repository.OfficeRepository;
import com.telran.employeeweb.repository.PersonalDetailRepository;
import com.telran.employeeweb.repository.ProjectRepository;
import com.telran.employeeweb.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class EmployeeServiceImplTest {

    private EmployeeRepository repository;
    private PersonalDetailRepository personalDetailRepository;
    private OfficeRepository officeRepository;
    private ProjectRepository projectRepository;
    private EmployeeService service;
    private List<Employee> list;

    @BeforeEach
    public void init(){
        repository = Mockito.mock(EmployeeRepository.class);
        personalDetailRepository = Mockito.mock(PersonalDetailRepository.class);
        officeRepository = Mockito.mock(OfficeRepository.class);
        repository = Mockito.mock(EmployeeRepository.class);
        service = new EmployeeServiceImpl(repository, personalDetailRepository,
                officeRepository, projectRepository);

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employee1.setName("Tom");
        employee1.setSurname("Wehner");
        employee1.setAge(40);
        employee2.setName("Martin");
        list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);

        Mockito.when(repository.findAll()).thenReturn(list);
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(employee1));
        Mockito.when(repository.findById("testId2")).thenReturn(Optional.empty());
    }

    @Test
    public void testGetEmployees(){
        List<Employee> employees = service.getEmployees();
        assertEquals(list.get(0).getName(), employees.get(0).getName());
        assertEquals(list.get(1).getName(), employees.get(1).getName());
    }

    @Test
    public void testUpdateEmployeeSurnameAndAge(){
        Employee employee1 = list.get(0);
        assertEquals(employee1.getSurname(), "Wehner");
        assertEquals(employee1.getAge(), 40);
        service.updateEmployeeSurnameAndAge("testId", "New Surname", 50);
        assertEquals(employee1.getSurname(), "New Surname");
        assertEquals(employee1.getAge(), 50);
        Mockito.verify(repository).save(employee1);

        service.updateEmployeeSurnameAndAge("testId2", "New Surname", 50);
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

}
package com.telran.employeeweb.service.impl;

import com.telran.employeeweb.model.entity.Employee;
import com.telran.employeeweb.model.entity.Office;
import com.telran.employeeweb.model.entity.PersonalDetail;
import com.telran.employeeweb.repository.EmployeeRepository;
import com.telran.employeeweb.repository.OfficeRepository;
import com.telran.employeeweb.repository.PersonalDetailRepository;
import com.telran.employeeweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    private final PersonalDetailRepository personalDetailRepository;

    private final OfficeRepository officeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository,
                               PersonalDetailRepository personalDetailRepository,
                               OfficeRepository officeRepository) {
        this.repository = repository;
        this.personalDetailRepository = personalDetailRepository;
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
//        return repository.findAll(Sort.by("name").ascending());
//        Page<Employee> page = repository.findAll(PageRequest.of(0, 5, Sort.by("surname")));
//        return page.getContent();
//        return repository.specialQuery();
//        return repository.specialQueryTwo("Fox");
//        return repository.specialQueryThree("Fox");
        return repository.findAll();
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Employee> findEmployeeByNameOrSurname(String name, String surname) {
        return repository.findAllByNameOrSurname(name, surname);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Employee> findAllByAgeGreaterThanEqual(Integer age, Pageable pageable) {
        return repository.findAllByAgeGreaterThanEqual(age, pageable);
    }

    @Override
    public Employee addOrUpdate(Employee employee) {
        // This is for example, personalDetail should be filled from the request
        PersonalDetail personalDetail = new PersonalDetail();
        personalDetail.setPassportData(employee.getName() + " passport data");
        personalDetail.setHomeAddress("Home address");
        personalDetail = personalDetailRepository.save(personalDetail);
        employee.setPersonalDetail(personalDetail);

        // This is for example, office should be filled from the request
//        Office office = officeRepository.findById(1L).get(); // bad practice
        Office office = officeRepository.getReferenceById(1L);
        employee.setOffice(office);

        return repository.save(employee);
    }

    @Override
    public Employee updateEmployeeSurnameAndAge(String id, String surname, int age) {
        Optional<Employee> byId = repository.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            employee.setSurname(surname);
            employee.setAge(age);
            return repository.save(employee);
        }
        return null;
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }
}

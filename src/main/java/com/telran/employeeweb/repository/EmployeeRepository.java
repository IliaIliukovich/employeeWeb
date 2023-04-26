package com.telran.employeeweb.repository;

import com.telran.employeeweb.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByNameOrSurname(String name, String surname);

    List<Employee> findTop3ByAgeAfterOrderByAgeDesc(Integer age);

    Page<Employee> findAllByAgeGreaterThanEqual(Integer age, Pageable pageable);

    @Query("select e from Employee e where e.name = 'Bob'")
    List<Employee> specialQuery();

    @Query("select e from Employee e where e.surname = ?1")
    List<Employee> specialQueryTwo(String s);

    @Query("select e from Employee e where e.surname = :surname")
    List<Employee> specialQueryThree(@Param("surname") String surname);

}

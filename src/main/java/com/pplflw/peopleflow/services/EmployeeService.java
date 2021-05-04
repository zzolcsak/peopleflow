package com.pplflw.peopleflow.services;

import com.pplflw.peopleflow.models.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeEntity> findById(Integer id);
    List<EmployeeEntity> findAll();
    EmployeeEntity createEmployee(EmployeeEntity employee);
    void deleteEmployee(EmployeeEntity employee);
}

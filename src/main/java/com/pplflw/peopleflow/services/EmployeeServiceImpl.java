package com.pplflw.peopleflow.services;

import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;


    @Override
    public Optional<EmployeeEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteEmployee(EmployeeEntity employee) {
        repository.delete(employee);
    }
}

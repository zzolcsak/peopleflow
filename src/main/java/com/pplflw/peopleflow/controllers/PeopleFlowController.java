package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.api.EmployeeApi;
import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.models.EmployeeState;
import com.pplflw.peopleflow.services.EmployeeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
public class PeopleFlowController implements EmployeeApi {
    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> employeeGetById(@Min(1) @ApiParam(value = "id of the employee", required = true) @PathVariable("id") Integer id) {
        Optional<EmployeeEntity> employee = employeeService.findById(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get().toEmployee(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> employeeCreate(@ApiParam(value = "employee information", required = true) @Valid @RequestBody Employee employee) {
        employee.setState(new EmployeeState()
                .state(EmployeeState.StateEnum.ADDED));
        EmployeeEntity created = employeeService.createEmployee(EmployeeEntity.fromEmployee(employee));
        return new ResponseEntity<>(created.toEmployee(), HttpStatus.CREATED);
    }
}

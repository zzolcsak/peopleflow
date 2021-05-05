package com.pplflw.peopleflow.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.models.EmployeeStateEntity;
import com.pplflw.peopleflow.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

public class EmployeeStateChangedConsumer {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${tpd.change-state-topic}",
            groupId="${spring.kafka.consumer.group-id}")
    public void stateChanged(String employeeJson) throws JsonProcessingException {
        var employeeWithNewState = objectMapper
                .readValue(employeeJson, Employee.class);
        var newState = employeeWithNewState.getState();
        Optional<EmployeeEntity> employeeToUpdate = employeeService
                .findById(employeeWithNewState.getId());
        employeeToUpdate.ifPresent(e -> e.setState(EmployeeStateEntity
                .fromEmployeeState(newState)));
        employeeToUpdate.ifPresent(employeeService::updateEmployee);
    }
}

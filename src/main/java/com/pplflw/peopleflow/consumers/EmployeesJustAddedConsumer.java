package com.pplflw.peopleflow.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.models.EmployeeState;
import com.pplflw.peopleflow.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeesJustAddedConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeService employeeService;

    @KafkaListener(topics = "${tpd.topic-name}",
            groupId="${spring.kafka.consumer.group-id}")
    public void saveWithStateAdded(String message)
            throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        employee.state(new EmployeeState().state(EmployeeState.StateEnum.ADDED));
        employeeService.createEmployee(EmployeeEntity.fromEmployee(employee));
    }
}

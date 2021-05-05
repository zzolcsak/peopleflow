package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.api.EmployeeApi;
import com.pplflw.peopleflow.models.Employee;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
public class PeopleFlowController implements EmployeeApi {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${tpd.topic-name}")
    String tpdTopicName;
    @Value("${tpd.change-state-topic}")
    String changeStateTopic;

    @Override
    public ResponseEntity<Void> employeeCreate(@ApiParam(value = "employee information", required = true) @Valid @RequestBody Employee employee) {
        sendToTopic(employee, tpdTopicName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> employeeChangeState(@Min(1) @ApiParam(value = "id of the employee", required = true) @PathVariable("id") Integer id, @ApiParam(value = "employee information", required = true) @Valid @RequestBody Employee employee) {
        sendToTopic(employee, changeStateTopic);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void sendToTopic(Employee employee, String topic) {
        kafkaTemplate.send(topic, employee.getName(), employee);
    }
}

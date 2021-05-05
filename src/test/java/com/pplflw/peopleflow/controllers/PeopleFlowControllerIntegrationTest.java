package com.pplflw.peopleflow.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pplflw.peopleflow.consumers.EmployeesJustAddedConsumer;
import com.pplflw.peopleflow.models.Employee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092", "port = 9092"})
@ActiveProfiles("test")
class PeopleFlowControllerIntegrationTest {
    @Autowired
    EmployeesJustAddedConsumer employeesJustAddedConsumer;
    @Autowired
    PeopleFlowController peopleFlowController;

    @Test
    @Disabled("couldn't get this to work")
    void givenEmployee_whenCallingEmployeeCreate_thenSendsToRightConsumer() throws JsonProcessingException {
        Employee employee = new Employee().age(66)
                .contractInformation("great info")
                .name("Johnny B. Goode");
        EmployeesJustAddedConsumer spyConsumer = spy(employeesJustAddedConsumer);
        peopleFlowController.employeeCreate(employee);
        verify(spyConsumer, times(1))
                .saveWithStateAdded(anyString());
    }

}

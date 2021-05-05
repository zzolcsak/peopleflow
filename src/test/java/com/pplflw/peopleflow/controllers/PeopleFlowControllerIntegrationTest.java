package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.consumers.EmployeesJustAddedConsumer;
import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
//    @Spy
//    EmployeeService employeeService;

    @Test
    void givenEmployee_whenCallingEmployeeCreate_thenSendsToRightConsumer() throws ExecutionException, InterruptedException {
        Employee employee = new Employee().age(66)
                .contractInformation("great info")
                .name("Johnny B. Goode");
        peopleFlowController.employeeCreate(employee);
//        Executors.newSingleThreadScheduledExecutor()
//                .schedule(() -> assertEquals(1, employeeService.findAll().size())//verify(employeeService).createEmployee(any())
//                , 35, TimeUnit.SECONDS)
//        .get();
//        assertEquals(1, employeeService.findAll().size());
    }

}

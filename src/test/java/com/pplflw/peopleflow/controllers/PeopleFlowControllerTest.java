package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.models.Employee;
import com.pplflw.peopleflow.models.EmployeeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleFlowControllerTest {
    @InjectMocks
    @Spy
    PeopleFlowController peopleFlowController = new PeopleFlowController();
    @Mock
    @SuppressWarnings("unused") // injected into the controller
    //verified as peopleFlowController.kafkaTemplate
    KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void givenEmployee_whenCallingEmployeeCreate_thenSendsItToKafka() {
        //given
        Employee expected = new Employee()
                .name("John Smith")
                .age(33)
                .contractInformation("info")
                .id(33);
        //when
        peopleFlowController.tpdTopicName = "employee-topic";
        peopleFlowController.employeeCreate(expected);
        //then
        verify(peopleFlowController.kafkaTemplate, times(1)).send("employee-topic", "John Smith", expected.state(new EmployeeState().state(EmployeeState.StateEnum.ADDED)));
        verifyNoMoreInteractions(peopleFlowController.kafkaTemplate);
    }
}

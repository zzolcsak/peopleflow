package com.pplflw.peopleflow.controllers;

import com.pplflw.peopleflow.models.EmployeeEntity;
import com.pplflw.peopleflow.models.EmployeeStateEntity;
import com.pplflw.peopleflow.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeopleFlowControllerTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    @Spy
    PeopleFlowController peopleFlowController = new PeopleFlowController();

    @Test
    public void whenCallingGetEmployee_callsEmployeeServiceGet() {
        // when
        EmployeeEntity expected = new EmployeeEntity();
        expected.setState(EmployeeStateEntity.ADDED);

        when(employeeService.findById(any()))
                .thenReturn(Optional.of(expected));
        peopleFlowController.employeeGetById(1);
        // then
        verify(employeeService).findById(1);
    }
}

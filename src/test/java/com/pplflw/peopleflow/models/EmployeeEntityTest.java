package com.pplflw.peopleflow.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeEntityTest {
    @ParameterizedTest(name = "{0} to {1} will be {2}")
    @CsvSource({
            "ADDED, IN_CHECK, IN_CHECK",
            "IN_CHECK, APPROVED, APPROVED",
            "APPROVED, ACTIVE, ACTIVE",
            "ADDED, APPROVED, ADDED",
            "ADDED, ACTIVE, ADDED",
            "IN_CHECK, ACTIVE, IN_CHECK",
            "IN_CHECK, ADDED, IN_CHECK",
            "APPROVED, ADDED, APPROVED",
            "APPROVED, IN_CHECK, APPROVED",
            "ACTIVE, ADDED, ACTIVE",
            "ACTIVE, IN_CHECK, ACTIVE",
            "ACTIVE, APPROVED, ACTIVE",
            "ACTIVE, ACTIVE, ACTIVE",
            "APPROVED, APPROVED, APPROVED",
            "IN_CHECK, IN_CHECK, IN_CHECK",
            "ADDED, ADDED, ADDED",
    })
    void whenCallingSetState_canOnlySetToNextState(String old, String newValue, String result) {
        var oldState = EmployeeStateEntity.valueOf(old);
        var newState = EmployeeStateEntity.valueOf(newValue);
        var resultingState =
                EmployeeStateEntity.valueOf(result);
        EmployeeEntity e = new EmployeeEntity();
        e.setState(oldState);
        assertEquals(oldState, e.getState());
        e.setState(newState);
        assertEquals(resultingState, e.getState());
    }

}

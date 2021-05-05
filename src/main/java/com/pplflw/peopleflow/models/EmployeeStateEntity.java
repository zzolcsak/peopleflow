package com.pplflw.peopleflow.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum EmployeeStateEntity {
    ADDED(1), IN_CHECK(2), APPROVED(3), ACTIVE(4);

    @Id
    private Integer id;

    private EmployeeStateEntity(Integer id) {
        this.id = id;
    }

    public static EmployeeStateEntity fromEmployeeState(EmployeeState state) {
        switch (state.getState()) {
            case ADDED: return ADDED;
            case IN_CHECK: return IN_CHECK;
            case ACTIVE: return ACTIVE;
            case APPROVED: return APPROVED;
            default:
                throw new IllegalStateException("Employee state not recognized: "
                        + state.getState());
        }
    }

    public EmployeeState toEmployeeState() {
        switch (this) {
            case ADDED: return new EmployeeState()
                    .state(EmployeeState.StateEnum.ADDED);
            case IN_CHECK: return new EmployeeState()
                    .state(EmployeeState.StateEnum.IN_CHECK);
            case ACTIVE: return new EmployeeState()
                    .state(EmployeeState.StateEnum.ACTIVE);
            case APPROVED: return new EmployeeState()
                    .state(EmployeeState.StateEnum.APPROVED);
            default:
                throw new IllegalStateException("Employee state entity not recognized: "
                        + this);
        }
    }

    public boolean directlyPrecedes(EmployeeStateEntity state) {
        switch(this) {
            case ADDED: return state.equals(IN_CHECK);
            case IN_CHECK: return state.equals(APPROVED);
            case APPROVED: return state.equals(ACTIVE);
            default: return false;
        }
    }
}

package com.pplflw.peopleflow.models;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String contractInformation;
    private EmployeeStateEntity state = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContractInformation() {
        return contractInformation;
    }

    public void setContractInformation(String contractInformation) {
        this.contractInformation = contractInformation;
    }

    public EmployeeStateEntity getState() {
        return state;
    }

    public void setState(EmployeeStateEntity state) {
        if (this.state == null || this.state.directlyPrecedes(state)) {
            this.state = state;
        }
    }

    public static EmployeeEntity fromEmployee(Employee employee) {
        var e = new EmployeeEntity();
        e.setId(employee.getId());
        e.setName(employee.getName());
        e.setContractInformation(employee.getContractInformation());
        e.setAge(employee.getAge());
        e.setState(EmployeeStateEntity.fromEmployeeState(employee.getState()));
        return e;
    }

    public Employee toEmployee() {
        return new Employee()
                .id(id)
                .name(name)
                .age(age)
                .contractInformation(contractInformation)
                .state(state.toEmployeeState());
    }
}
